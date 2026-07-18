# Cached weather API wrapper with `net/http`

## Concept

This migrated snapshot wraps Visual Crossing weather data with Redis caching, JSON HTTP handlers, and an in-memory per-IP rate limiter.

## Hypothesis

A thin standard-library HTTP layer can shield callers from a third-party API while caching repeated address lookups and bounding request bursts.

## Architecture

`main.go` loads indirect configuration, verifies Redis connectivity, wires the cache and weather service, and registers `/ping` and `/weather`. Cache keys are SHA-256 hashes of addresses. Cache misses call Visual Crossing and are stored for a random 5-to-15-minute TTL. The weather route allows two requests per second with a burst of five per observed IP.

## Quick path

Prerequisites: the Go version declared in `go.mod`, `podman-compose`, Redis, and a Visual Crossing API key.

```bash
podman-compose --file ./_devenv/docker-compose.yml --project-name weather-service up -d
export WEATHER_SERVICE_REDIS_CONN=localhost:6379
export WEATHER_SERVICE_VISUAL_CROSSING_API_KEY='<your-key>'
go run .
curl http://localhost:8080/ping
curl 'http://localhost:8080/weather?address=Mexico%20City'
```

The environment variable names are mapped by `config.properties`; the file contains names only, never secret values. `example.env.txt` is the safe reference for expected variables.

## Commands and verification

```bash
go test ./...
go build ./...
podman-compose --file ./_devenv/docker-compose.yml --project-name weather-service down
```

Results on 2026-07-18: `go test ./...` passed across nine packages and reported no test files; `go build ./...` passed with no output. Full runtime verification was skipped because Redis and a Visual Crossing credential were not provisioned. `api_test.http` contains local requests and a placeholder for the upstream credential.

## Expected behavior

`GET /ping` returns `{}`. `GET /weather?address=...` returns upstream JSON, reusing Redis on repeated normalized input strings. A missing address returns 400, unavailable locations return 404, internal failures return 500, and excess bursts return 429.

## Tradeoffs and status

Status: **migrated snapshot**. Configuration values point to environment-variable names rather than values, which adds indirection. The upstream request uses `http.Get` without an explicit timeout, cache writes ignore errors, rate-limiter entries are never evicted, and address strings are interpolated into a URL without explicit path escaping. These are preserved source behaviors and production risks, not recommended defaults.

## Agent boundaries

Do not copy source `CLAUDE.md` instructions or introduce local agent rules; root `AGENTS.md` remains canonical. Preserve behavior unless a focused follow-up addresses a documented risk. Do not commit real environment files, Redis data, private HTTP environments, binaries, temporary responses, screenshots, or Air build output.
