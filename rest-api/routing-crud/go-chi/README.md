# Routing CRUD with Go and Chi

## Concept

This experiment uses Chi to organize CRUD-shaped HTTP routes while proxying post data from JSONPlaceholder.

## Hypothesis or problem

A small resource router can keep route grouping and request handlers understandable without a larger web framework. The experiment is useful if each post operation remains directly traceable from route to upstream request.

## Architecture

| Boundary | Role |
| --- | --- |
| `main.go` | Starts the HTTP server and mounts the post resource. |
| `posts.go` | Defines routes, extracts post IDs, and proxies requests. |
| JSONPlaceholder | Supplies the external post API. |

Requests flow from Chi routes to a handler and then to `https://jsonplaceholder.typicode.com`.

## Quick path

Prerequisites: Go 1.26 and network access to JSONPlaceholder.

```sh
go mod download
go run .
curl http://localhost:8080/posts/
```

## Commands

```sh
go test ./...
go build ./...
go run .
```

`Makefile` provides equivalent dependency and run shortcuts.

## Configuration

Set `PORT` to override the default HTTP port `8080`. No credentials are required. `requests.http` contains safe request examples.

## Expected behavior

`GET /` returns `Hello World!`. Routes below `/posts/` proxy list, create, read, update, and delete operations. Upstream failures are returned as server errors; upstream HTTP status codes are not preserved by this learning snapshot.

## Tradeoffs

The handlers use the default HTTP client without explicit timeouts and proxy response bodies with minimal validation. This keeps routing visible but is not a production-ready proxy design.

## Status

**Migrated snapshot.** Behavior is preserved from `golang-projects/go-chi-restful-api`.

## Verification

Verified on 2026-07-18:

- `gofmt -l .` returned no files.
- `go test ./...` passed with `[no test files]`.
- `go build ./...` passed.
- `go vet ./...` passed.
- Runtime HTTP requests were skipped because post operations require an external service; no runtime result is claimed.

## Agent boundaries

Inherit the root `AGENTS.md`. Preserve the routing experiment, avoid introducing external calls in automated tests, and keep generated responses and binaries out of the snapshot.
