# TMDB API client CLI with Cobra

## Concept

This migrated snapshot places a typed TMDB HTTP client behind a Cobra command interface and renders movie lists as terminal tables.

## Hypothesis

A small client boundary can keep request construction and response decoding separate from command selection and presentation, while an `HTTPClient` interface leaves room for isolated tests.

## Architecture

`main.go` registers the `movies` command. `cmd/movies` validates `--type`, selects one of four client operations, and renders results. `internal/tmdb` owns authenticated HTTP requests, a ten-second production timeout, and response models.

## Quick path

Prerequisites: the Go version declared in `go.mod` and a TMDB API read-access token.

```bash
cp .env.example .env
# Set TMDB_API_KEY in .env without committing it.
task topcmd
```

Alternatively, export `TMDB_API_KEY` and run `go run . movies --type top`. Supported values are `top`, `playing`, `popular`, and `upcoming`.

## Commands and verification

```bash
go test ./...
go build ./...
```

Results on 2026-07-18: `go test ./...` passed across four packages and reported no test files; `go build ./...` passed with no output. The live CLI path was skipped because no TMDB credential was supplied. `api.http` uses a `{{TMDB_API_KEY}}` placeholder but writes response files; those outputs are intentionally ignored and were not migrated.

## Configuration

`TMDB_API_KEY` is required and sent as a bearer token. `.env.example` is safe to copy; `.env` and private HTTP environments must remain untracked.

## Expected behavior

A supported movie type prints a titled table of movie title, release date, and popularity. A missing token, unsupported type, request failure, or non-200 response exits with status 1.

## Tradeoffs and status

Status: **migrated snapshot**. The implementation has duplicated endpoint methods and exits directly from command helpers, which keeps the exercise small but limits testability. No differential documentation from the secondary checkout was incorporated because its README matched the canonical source.

## Agent boundaries

Keep live API calls out of default verification and use fake `HTTPClient` implementations for future unit tests. Do not commit tokens, binaries, generated response captures, or private HTTP environments.
