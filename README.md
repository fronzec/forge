# ⚒️ forge

A space for experimenting with emerging standards, architectures, and engineering concepts.

Here, ideas are tested through working software: assumptions are challenged, tradeoffs are made explicit, and learning matters more than convention.

## Experiments

Use the [concept guidelines](docs/concepts.md) to place experiments by engineering question. New experiments should also follow the [experiment README guideline](docs/experiment-readme-guideline.md) so their intent, execution, and verification remain useful to both humans and agents.

| Experiment | Purpose |
| --- | --- |
| [API client CLI / TMDB + Cobra](api-client-cli/tmdb/go-cobra/) | Explore a typed API client behind a Cobra command interface. |
| [API wrapper / cached weather + `net/http`](api-wrapper/cached-weather/go-net-http/) | Wrap a weather API with Redis caching and per-IP rate limiting. |
| [Application scaffolding / Express Generator](application-scaffolding/express-generator/node-express/) | Record the conventional routers, views, middleware, and error boundaries generated for Express. |
| [Batch processing / generic jobs with GoBatch](batch-processing/generic-jobs/go-gobatch/) | Preserve a parked reader-processor-writer and partitioning proof of concept. |
| [Batch processing / Spring Batch log aggregation](batch-processing/log-aggregation/java-spring-batch/) | Import CSV log records through a chunk-oriented batch job. |
| [Content management / Markdown notes with SQLite](content-management/markdown-notes/go-sqlite/) | Persist notes behind a repository boundary with a framework-free client. |
| [Database sharding / order service with Spring and ShardingSphere](database-sharding/order-service/java-spring-shardingsphere/) | Route orders across two MySQL databases by order ID parity. |
| [Declarative desktop UI / Qt Quick + PySide6](declarative-desktop-ui/qt-quick/python-pyside6/) | Compare a QML view with a minimal Python bootstrap. |
| [Event-driven desktop UI / Qt Widgets + PySide6](event-driven-desktop-ui/qt-widgets/python-pyside6/) | Demonstrate widget composition and signal-slot interaction. |
| [HTTP QUERY / Node.js + Fastify](http-query-rfc/node-fastify/) | Explore body-bearing HTTP QUERY requests with Fastify. |
| [In-memory storage / HTTP key-value store](in-memory-storage/key-value-store/go-http/) | Expose a concurrent map through HTTP with Prometheus instrumentation. |
| [Numeric precision / money with decimal libraries](numeric-precision/money/go-decimal/) | Compare floating-point pitfalls with rational and decimal representations. |
| [REST API / DNF history with FastAPI](rest-api/system-history/python-fastapi/) | Parse local DNF transaction history through an asynchronous HTTP endpoint. |
| [REST API / routing CRUD with Chi](rest-api/routing-crud/go-chi/) | Explore resource-oriented routing while proxying CRUD requests. |
| [REST API / TODO service with Express and Sequelize](rest-api/todo-service/node-express-sequelize/) | Separate routing, controllers, and PostgreSQL persistence in a small task service. |
| [Web framework basics / Express Hello World](web-framework-basics/hello-world/node-express/) | Demonstrate the minimum Express route and server lifecycle. |
| [Web framework basics / FastAPI request validation](web-framework-basics/request-validation/python-fastapi/) | Demonstrate typed path, query, and body validation with FastAPI and Pydantic. |
