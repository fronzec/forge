# TODO REST Service

## Concept

This migrated snapshot demonstrates a small resource-oriented HTTP service backed by PostgreSQL through Sequelize models and migrations.

## Hypothesis or problem

An Express controller can keep HTTP routing separate from relational persistence while Sequelize owns schema migration and model access.

## Architecture

- `src/index.js` creates the Express server on port `3000`.
- `src/routes/` maps the TODO collection endpoints.
- `src/controllers/` coordinates HTTP responses and Sequelize operations.
- `src/models/` and `src/db/migrations/` define the persistence boundary.
- `devEnv/docker-compose.yml` runs the application and PostgreSQL.

## Quick path

Prerequisites: Node.js, npm, and `podman-compose` for the complete runtime.

```sh
npm ci
npm test -- --runInBand
cp devEnv/example.env devEnv/.env
podman-compose -f devEnv/docker-compose.yml up --build
```

Then use `requests.http` or call:

```sh
curl http://localhost:3000/api/todos
```

## Configuration

`devEnv/.env` must define `DB_USER`, `DB_PASS`, `DB_NAME`, and `DB_HOST`. The committed `devEnv/example.env` contains local-only placeholders. Production and test configurations read the same variables.

## Expected behavior

- `POST /api/todos` creates a pending task and returns HTTP `201`.
- `GET /api/todos` returns the persisted task collection.
- Other routes return the snapshot's `Hello World!` fallback.

## Tradeoffs

The snapshot has only a Jest setup test; it does not test the HTTP or database boundaries. Compose uses PostgreSQL 9.6, startup does not wait for database readiness, migrations run during application startup, and the historical dependencies may contain known vulnerabilities.

## Status

Migrated learning snapshot from `fronzec/javascript-projects/todo-v1` at source commit `1ba7330`.

## Verification

Verified on 2026-07-18:

- `npm ci` installed the locked dependencies successfully and reported 71 vulnerabilities (5 low, 37 moderate, 22 high, 7 critical).
- `node --check` passed for all application, migration, and test JavaScript.
- `npm test -- --runInBand` passed 1 test in 1 suite.
- The PostgreSQL-backed runtime was not executed because the local Podman machine was unavailable. `podman-compose` 1.0.6 was found under Python 3.11.9 and Podman 5.8.2 was installed, but its socket refused the connection.
