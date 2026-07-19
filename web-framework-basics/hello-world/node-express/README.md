# Express Hello World

## Concept

This migrated snapshot demonstrates the smallest useful Express application: create an app, register one route, and listen on an HTTP port.

## Hypothesis or problem

A single route is enough to expose the essential Express request-response flow without introducing application structure or persistence.

## Architecture

`index.js` creates the Express application, handles `GET /`, and starts the server on port `3000`.

## Quick path

Prerequisites: Node.js and npm.

```sh
npm ci
npm run run
curl http://localhost:3000/
```

The companion request is available in `requests.http`.

## Expected behavior

`GET /` returns `Hello World from ExpressJs!`. Unregistered paths use Express's default `404` response.

## Tradeoffs

The fixed port and startup side effect keep the example small, but make isolated testing and embedding harder. The dependencies are intentionally preserved from the source snapshot and may contain known vulnerabilities.

## Status

Migrated snapshot from `fronzec/javascript-projects/hello-world` at source commit `1ba7330`.

## Verification

Verified on 2026-07-18:

- `npm ci` installed the locked dependencies successfully and reported 7 vulnerabilities (3 low, 4 high).
- `node --check index.js` passed.
- A locally started process returned the expected body from `GET /`.
