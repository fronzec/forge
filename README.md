# forge

A space for experimenting with emerging standards, architectures, and engineering concepts.

Here, ideas are tested through working software: assumptions are challenged, tradeoffs are made explicit, and learning matters more than convention.

## Experiments

New experiments should follow the [experiment README guideline](docs/experiment-readme-guideline.md) so their intent, execution, and verification remain useful to both humans and agents.

| Experiment | Status | Purpose |
| --- | --- | --- |
| [HTTP QUERY / Node.js + Fastify](http-query-rfc/node-fastify/) | Active experiment | Explore body-bearing HTTP QUERY requests with Fastify. |
| [Declarative desktop UI / Qt Quick + PySide6](declarative-desktop-ui/qt-quick/python-pyside6/) | Migrated snapshot | Compare a QML view with a minimal Python bootstrap. |
| [Event-driven desktop UI / Qt Widgets + PySide6](event-driven-desktop-ui/qt-widgets/python-pyside6/) | Migrated snapshot | Demonstrate widget composition and signal-slot interaction. |
| [Batch processing / Spring Batch log aggregation](batch-processing/log-aggregation/java-spring-batch/) | Migrated snapshot | Import CSV log records through a chunk-oriented batch job. |
| [API client CLI / TMDB + Cobra](api-client-cli/tmdb/go-cobra/) | Migrated snapshot | Explore a typed API client behind a Cobra command interface. |
| [API wrapper / cached weather + `net/http`](api-wrapper/cached-weather/go-net-http/) | Migrated snapshot | Wrap a weather API with Redis caching and per-IP rate limiting. |
