# Generic Batch Jobs with GoBatch

## Concept

This experiment maps the reader-processor-writer, chunk, partition, and job-metadata model from Spring Batch onto Go with `chararch/gobatch`.

## Hypothesis or problem

A framework can express a CSV-to-database batch job with explicit stages and persisted execution metadata. The historical run inserted all 1,000 fixture rows and reported `COMPLETED`, but the framework's maintenance state challenges its suitability for new systems.

## Architecture

| Boundary | Role |
| --- | --- |
| `main.go` | Configures GoBatch metadata and launches the job. |
| `job1/` | Builds a partitioned CSV reader, processor, and MySQL writer. |
| `db/` | Creates application connections and environment-based DSNs. |
| `resources/` | Contains reproducible 1k and 10k CSV input fixtures. |
| `_devenvironment/` | Provides MySQL Compose and schema setup. |

## Quick path

Prerequisites: Go 1.26, `podman-compose`, and Podman.

```sh
cd _devenvironment
cp compose.env.example compose.env
podman-compose --env-file compose.env -p batch-service up -d
podman-compose --env-file compose.env -p batch-service exec -T db sh -c 'mysql -uroot -p"$MYSQL_ROOT_PASSWORD" gobatchservicedb' < db/00_schema_mysql_gobatch.sql
podman-compose --env-file compose.env -p batch-service exec -T db sh -c 'mysql -uroot -p"$MYSQL_ROOT_PASSWORD" gobatchservicedb' < db/01_schema_mysql_application.sql
cd ..
go run .
```

## Commands

```sh
go test ./...
go build ./...
go run .
task podmancompose-up
podman-compose -p batch-service -f _devenvironment/docker-compose.yml down
```

## Configuration

The application reads `DB_USERNAME`, `DB_PASSWORD`, `DB_HOST`, `DB_PORT`, and `DB_NAME`, with safe local placeholders in `db/config.go`. Set a non-empty `DB_PASSWORD` in the ignored `_devenvironment/compose.env` copied from the example.

## Expected behavior

The active job reads `resources/sample-persons-1k.csv`, processes records, and writes them to MySQL while GoBatch stores job and step execution metadata. Reusing the same `execAttempt` can cause a completed job to be skipped.

## Tradeoffs

`chararch/gobatch` is unmaintained, the job uses 100 partitions, local credentials are placeholders, and execution identity is hard-coded. For production Go batch work, prefer explicit worker pools using `errgroup` and channels or an actively maintained workflow system.

## Status

**Archived learning / parked proof of concept.** Migration preserves the learning record and does not imply maintenance, security hardening, or production readiness.

## Verification

Verified on 2026-07-18:

- `gofmt -l .` returned no files.
- `go test ./...` passed for all four packages with `[no test files]`.
- `go build ./...` passed.
- `go vet ./...` passed.
- Runtime execution was skipped because no disposable MySQL instance was started. The historical 1,000-row result is source-repository evidence, not a fresh runtime claim.

## Agent boundaries

Inherit the root `AGENTS.md`. Do not upgrade or revive GoBatch implicitly, do not commit local environment files or database data, and preserve the archived-learning status.
