# Request Validation with FastAPI

## Concept

This migrated snapshot demonstrates how FastAPI derives path, query, and request-body validation from Python type annotations and Pydantic models.

## Hypothesis or problem

Typed endpoint signatures can keep validation rules close to HTTP handlers while generating an inspectable OpenAPI contract and interactive documentation.

## Architecture

| Boundary | Role |
| --- | --- |
| `main.py` | Defines typed models and endpoints for path, query, scalar body, embedded body, and multi-body validation. |
| `requests.http` | Exercises representative successful requests and generated documentation. |
| FastAPI and Pydantic | Convert annotations into runtime validation and OpenAPI metadata. |

## Quick path

Prerequisite: Python 3.11.

```sh
python -m venv .venv
.venv/bin/python -m pip install --requirement requirements.txt
.venv/bin/python -m uvicorn main:app
curl http://127.0.0.1:8000/hello/Forge
```

Open `http://127.0.0.1:8000/docs` or run the companion requests in `requests.http` to explore the validation cases.

## Expected behavior

- Typed path and query parameters reject values outside their declared constraints with HTTP `422`.
- Item bodies are parsed into Pydantic models and returned as JSON.
- `PUT /itemsoptional/{item_id}` accepts either an item body or no body.
- Multi-body and embedded-body endpoints demonstrate their distinct JSON shapes.

## Tradeoffs

The repeated endpoint shapes favor tutorial visibility over application structure. Data is not persisted, several names are intentionally reused, and the historical FastAPI/Pydantic generation is pinned for snapshot fidelity rather than production use.

## Status

Migrated snapshot from `fronzec/fastapi-projects/tutorial` at source commit `7a35799`.

## Verification

Verified on 2026-07-18 with Python 3.11.15:

- `uv pip install --python <temporary-venv>/bin/python --requirement requirements.txt` installed the pinned dependency set.
- `uv pip check --python <temporary-venv>/bin/python` reported that all installed packages are compatible.
- Compiling and importing `main.py` passed.
- Runtime smoke requests verified `GET /hello/Forge`, the bodyless optional-item update, and HTTP `422` for invalid constrained parameters.

## Agent boundaries

Inherit the root `AGENTS.md`. Preserve the request-validation focus, avoid introducing persistence, and keep generated environments and response files out of the snapshot.
