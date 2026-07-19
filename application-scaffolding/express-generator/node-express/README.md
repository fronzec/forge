# Express Generator Skeleton

## Concept

This migrated snapshot records the conventional application structure produced by `express-generator`: middleware assembly, routers, Jade views, static assets, and centralized HTTP error handling.

## Hypothesis or problem

A generated skeleton can accelerate discovery of an unfamiliar framework, provided the generated boundaries remain visible and the application can start from a clean checkout.

## Architecture

- `app.js` assembles middleware, routes, static assets, and error handlers.
- `routes/` contains the home and users routers.
- `views/` contains the Jade templates.
- `bin/www` creates the HTTP server on port `3000` by default.

## Quick path

Prerequisites: Node.js and npm.

```sh
npm ci
npm start
curl http://localhost:3000/
```

Set `DEBUG=hello-world-expgen:*` to enable namespaced debug output.

## Expected behavior

`GET /` renders the generated Express welcome page, `GET /users` returns a placeholder resource, and unknown routes render the generated error page.

## Tradeoffs

The source repository accidentally excluded `bin/www` through a generic Eclipse `bin/` ignore rule. Forge restores that standard generated bootstrap so the snapshot is runnable. Express 4, Jade, and the transitive dependencies remain at their historical versions and are not suitable as production defaults.

## Status

Migrated snapshot from `fronzec/javascript-projects/hello-world-expgen` at source commit `1ba7330`, with the missing generated server bootstrap restored.

## Verification

Verified on 2026-07-18:

- `npm ci` installed the locked dependencies successfully and reported 16 vulnerabilities (6 low, 1 moderate, 5 high, 4 critical).
- `node --check` passed for `app.js`, both routers, and `bin/www`.
- A locally started process rendered the expected Express welcome page from `GET /`.
