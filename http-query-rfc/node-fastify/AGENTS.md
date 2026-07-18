# Experiment Instructions

- Use Node.js ESM.
- Keep Fastify's custom `QUERY` registration configured with `hasBody: true`.
- Spanish API contract field names are intentional; keep them stable unless the task explicitly changes scope.
- Listen on port `3000` by default and honor the `PORT` environment variable.
- Verify syntax with `node --check server.js`, then send the README's `curl` request to a running server on the selected port.
- Keep this experiment minimal.
