# DNF History API with FastAPI

## Concept

This migrated snapshot exposes a local DNF transaction history through an asynchronous FastAPI endpoint.

## Hypothesis or problem

A small HTTP boundary can turn line-oriented system history into typed JSON without blocking the event loop while reading the source file.

## Architecture

| Boundary | Role |
| --- | --- |
| `main.py` | Reads `history.txt`, validates each transaction with Pydantic, and serves `GET /`. |
| `history.txt` | Provides a safe fixture with the three fields consumed by the parser. |
| `Dockerfile` | Runs the historical dependency set on Python 3.11 without the source repository's end-of-life Fedora image. |

## Quick path

Prerequisites: Python 3.11 or Podman.

```sh
python -m venv .venv
.venv/bin/python -m pip install --requirement requirements.txt
.venv/bin/python -m uvicorn main:app
curl http://127.0.0.1:8000/
```

The same request is available in `requests.http`.

## Container

```sh
podman build --tag forge-fastapi-dnf .
podman run --rm --publish 8000:8000 forge-fastapi-dnf
```

## Configuration

The application reads `history.txt` from its working directory. Replace the committed fixture with output shaped like `dnf history`, keeping the `ID | Command line | Date` columns first. Do not commit machine-specific package history.

## Expected behavior

`GET /` returns one JSON object per valid fixture line with numeric `id`, `command`, and `date` fields. Missing or malformed input fails the request rather than silently dropping records.

## Tradeoffs

The parser intentionally consumes only the first three pipe-delimited columns and reads the complete file for every request. The pinned FastAPI generation is preserved from the source period for snapshot fidelity and is not a production dependency baseline.

## Status

Migrated snapshot from `fronzec/fastapi-projects/hello-world` at source commit `7a35799`.

## Verification

Verified on 2026-07-18 with Python 3.11.15:

- `uv pip install --python <temporary-venv>/bin/python --requirement requirements.txt` installed the pinned dependency set.
- `uv pip check --python <temporary-venv>/bin/python` reported that all installed packages are compatible.
- Compiling and importing `main.py` passed.
- A local Uvicorn server returned both typed fixture transactions from `GET /`.
- The container build was skipped because Podman 5.8.2 could not connect to its stopped local machine.

## Agent boundaries

Inherit the root `AGENTS.md`. Keep system-specific history out of Git and preserve the bounded parsing experiment rather than expanding it into a package-management service.
