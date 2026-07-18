# Markdown Notes with Go and SQLite

## Concept

This experiment combines a plain Go HTTP API, a SQLite repository, and a framework-free browser client for persistent notes.

## Hypothesis or problem

A small content-management application can separate persistence behind a repository interface while keeping both HTTP and browser layers lightweight. Evidence is the ability to create, list, read, update, delete, and upload note content.

## Architecture

| Boundary | Role |
| --- | --- |
| `main.go` | Creates the database, exposes the API, and starts both servers. |
| `notes/` | Defines the note model, repository interface, and SQLite implementation. |
| `frontend/` | Provides static HTML, CSS, and JavaScript clients. |
| `notes.db` | Runtime-only SQLite database created in the experiment directory. |

## Quick path

Prerequisites: Go 1.26, a C toolchain for `go-sqlite3`, and ports `80` and `8080` available.

```sh
go mod download
go run .
```

Open `http://localhost/`; the unauthenticated API listens only on `http://127.0.0.1:8080`.

## Commands

```sh
go test ./...
go build ./...
go run .
task frontend-serve-py
rm -f notes.db
```

The separate frontend task is optional because `go run .` also serves `frontend/`.

## Configuration

There are no environment variables. The API is fixed to loopback port `8080`; unrestricted CORS is only suitable for this local experiment. `notes.db` is generated locally; remove it after the experiment with `rm -f notes.db`.

## Expected behavior

The API persists note titles and Markdown source in SQLite. It exposes create, upload, list, read, delete, update, and placeholder grammar-check endpoints. Despite the project name, rendering Markdown as HTML and grammar checking remain unimplemented.

## Tradeoffs

The snapshot uses global repository state, unrestricted development CORS, a 10 MB upload limit, fixed ports, and no authentication. Loopback binding limits exposure but does not make this a hardened content service.

## Status

**Migrated snapshot.** Temporary uploads, IDE state, and generated database files are intentionally excluded.

## Verification

Verified on 2026-07-18:

- `gofmt -l .` returned no files after formatting the migrated snapshot.
- `go test ./...` passed for both packages with `[no test files]`.
- `go build ./...` passed.
- `go vet ./...` passed.
- Runtime execution was skipped because it binds privileged port `80` and creates `notes.db`; no runtime result is claimed.

## Agent boundaries

Inherit the root `AGENTS.md`. Preserve the repository experiment and keep SQLite databases, uploaded files, IDE state, and other generated data out of version control.
