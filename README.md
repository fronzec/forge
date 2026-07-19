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
| [REST API / routing CRUD with Chi](rest-api/routing-crud/go-chi/) | Migrated snapshot | Explore resource-oriented routing while proxying CRUD requests. |
| [Content management / Markdown notes with SQLite](content-management/markdown-notes/go-sqlite/) | Migrated snapshot | Persist notes behind a repository boundary with a framework-free client. |
| [In-memory storage / HTTP key-value store](in-memory-storage/key-value-store/go-http/) | Migrated snapshot | Expose a concurrent map through HTTP with Prometheus instrumentation. |
| [Numeric precision / money with decimal libraries](numeric-precision/money/go-decimal/) | Migrated snapshot | Compare floating-point pitfalls with rational and decimal representations. |
| [Batch processing / generic jobs with GoBatch](batch-processing/generic-jobs/go-gobatch/) | Archived learning | Preserve a parked reader-processor-writer and partitioning proof of concept. |
| [Web framework basics / Express Hello World](web-framework-basics/hello-world/node-express/) | Migrated snapshot | Demonstrate the minimum Express route and server lifecycle. |
| [Application scaffolding / Express Generator](application-scaffolding/express-generator/node-express/) | Migrated snapshot | Record the conventional routers, views, middleware, and error boundaries generated for Express. |
| [REST API / TODO service with Express and Sequelize](rest-api/todo-service/node-express-sequelize/) | Migrated snapshot | Separate routing, controllers, and PostgreSQL persistence in a small task service. |
| [REST API / DNF history with FastAPI](rest-api/system-history/python-fastapi/) | Migrated snapshot | Parse local DNF transaction history through an asynchronous HTTP endpoint. |
