# HTTP QUERY with Node.js and Fastify

This experiment explores body-bearing HTTP `QUERY` requests, based on [RFC 10008](https://www.rfc-editor.org/rfc/rfc10008), through a small product-search API implemented with Node.js and Fastify.

## Run the Experiment

### Requirements

- Node.js 20 or later

### Install and Start

```bash
npm install
node server.js
```

The server listens on port `3000` by default. Set `PORT` to use another port.

### Send a QUERY Request

```bash
curl --request QUERY \
  --url http://localhost:3000/productos/buscar \
  --header 'content-type: application/json' \
  --data '{"categoria":"perifericos","precioMaximo":100}'
```

The response has this shape:

```json
{
  "productos": [
    {
      "id": 2,
      "nombre": "Mouse ergonomico",
      "precio": 75,
      "categoria": "perifericos",
      "tags": ["mouse", "ergonomia"]
    }
  ],
  "confirmacion": {
    "metodo": "QUERY",
    "filtrosAplicados": {
      "categoria": "perifericos",
      "precioMaximo": 100
    },
    "total": 1
  }
}
```

## Filters

| Field | Behavior |
| --- | --- |
| `categoria` | Exact category match after trimming surrounding whitespace. |
| `precioMaximo` | Includes products at or below a non-negative numeric value. |

Both filters are optional. An invalid `precioMaximo` returns HTTP `400`.

## Architecture

| File | Role |
| --- | --- |
| `server.js` | Registers the custom body-bearing `QUERY` method, defines the in-memory dataset and filters, and starts Fastify. |
| `package.json` | Declares the Node.js ESM package and Fastify dependency. |
| `package-lock.json` | Locks the dependency graph. |
| `AGENTS.md` | Provides experiment-specific agent instructions. |

## Experimental Scope

This is a focused standards experiment, not a production-ready service. It intentionally omits persistence, authentication, schemas, and a test suite.
