# In-memory Key-Value Store over Go HTTP

## Concept

This experiment exposes a concurrency-safe in-memory key-value store through HTTP and instruments it with Prometheus metrics.

## Hypothesis or problem

A mutex-protected map and a narrow storage interface can form a simple concurrent service boundary. The implementation also tests whether service and metrics behavior can be observed with a small local monitoring stack.

## Architecture

| Boundary | Role |
| --- | --- |
| `cmd/memkv/main.go` | Constructs the store and starts service and metrics listeners. |
| `store/` | Protects an in-memory map with `sync.RWMutex`. |
| `server/` | Maps `/keys` HTTP requests to store operations. |
| `metrics/` | Exposes Prometheus metrics on port `9100`. |
| `__scripts__/` | Configures Prometheus, Grafana, and optional Locust workloads. |

## Quick path

Prerequisite: Go 1.26.

```sh
go mod download
go run ./cmd/memkv
curl -X PUT --data 'value' http://localhost:4444/keys/example
curl http://localhost:4444/keys/example
```

## Commands

```sh
go test ./...
go build ./...
go run ./cmd/memkv
podman-compose up
```

The Compose and Locust assets are optional operational experiments.

## Configuration

The service uses fixed ports: `4444` for data, `9100` for metrics, `9090` for Prometheus, and `3000` for Grafana. Compose publishes development UIs and data only on loopback; Grafana requires authentication.

## Expected behavior

`PUT` or `POST /keys/{key}` stores a request body, `GET` returns it, `DELETE` removes it, and `GET /keys` lists keys as JSON. Values disappear when the process exits.

## Tradeoffs

The protocol does not distinguish a missing key from an empty value, unsupported methods receive no explicit error, keys are returned in nondeterministic order, and servers have no timeouts. Data and metrics use separate muxes. Metrics report nanoseconds rather than conventional seconds.

## Status

**Migrated snapshot.** The service and its monitoring/load-test assets are retained as learning material.

## Verification

Verified on 2026-07-18:

- `gofmt -l .` returned no files.
- `go test ./...` passed for all four packages with `[no test files]`.
- `go build ./...` passed.
- `go vet ./...` passed.
- Service, container, monitoring, and load-test runtime checks were skipped; no runtime result is claimed.

## Agent boundaries

Inherit the root `AGENTS.md`. Preserve the in-memory semantics and keep generated dashboards, load-test output, caches, and binaries out of the snapshot.
