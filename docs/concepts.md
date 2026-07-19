# Forge concept guidelines

Use concepts to group experiments by the primary engineering question they investigate. The hierarchy is concept first, experiment second, and language or runtime implementation last.

A concept is not a language, framework, business domain, delivery shape, or repository of origin. Those details describe how an experiment is implemented or delivered, not why it exists.

## Placement process

1. Identify the experiment's primary hypothesis or engineering question.
2. Compare that question with the admission boundaries and exclusions below.
3. Reuse an existing concept when its boundary fits, even if the technology or delivery shape differs.
4. Do not classify by language, framework, API style, executable type, or source repository.
5. If no existing concept fits, propose a new concept instead of creating it immediately.
6. Explain the proposed name, admission boundary, exclusions, why existing concepts do not fit, and which future experiments could reuse it.
7. Wait for explicit approval before updating the taxonomy or creating its directory.
8. After placement is approved, add or update the experiment record in the root [experiment table](../README.md#experiments).

## Target taxonomy

The taxonomy reflects current experiment evidence. It can evolve when new experiments expose a genuinely distinct engineering concern; it is not an immutable list.

| Concept | Admit when the primary question is about | Exclude when the primary question is about | Current experiments |
| --- | --- | --- | --- |
| `application-boundaries` | Application bootstrap, layering, or responsibility and dependency boundaries. | Protocol semantics or storage algorithms. | [Express Hello World](../web-framework-basics/hello-world/node-express/), [Express Generator](../application-scaffolding/express-generator/node-express/), [Markdown notes](../content-management/markdown-notes/go-sqlite/), [TODO service](../rest-api/todo-service/node-express-sequelize/) |
| `batch-processing` | Staged jobs, chunks, partitioning, or execution metadata. | Request-response APIs or general application layering without a batch lifecycle. | [GoBatch generic jobs](../batch-processing/generic-jobs/go-gobatch/), [Spring Batch log aggregation](../batch-processing/log-aggregation/java-spring-batch/) |
| `desktop-ui-architecture` | Desktop UI composition, responsibility boundaries, or event flow. | Web delivery or non-UI application structure. | [Qt Quick](../declarative-desktop-ui/qt-quick/python-pyside6/), [Qt Widgets](../event-driven-desktop-ui/qt-widgets/python-pyside6/) |
| `external-service-integration` | Consuming, adapting, caching, rate-limiting, or otherwise protecting access to an upstream service. | HTTP behavior owned by the local service or storage as the primary concern. | [TMDB client CLI](../api-client-cli/tmdb/go-cobra/), [cached weather](../api-wrapper/cached-weather/go-net-http/) |
| `http-semantics-and-contracts` | HTTP method semantics, routing, request validation, representations, or API contracts. | General application layering, upstream adaptation, or storage internals. | [HTTP QUERY](../http-query-rfc/node-fastify/), [Chi routing CRUD/proxy](../rest-api/routing-crud/go-chi/), [DNF history](../rest-api/system-history/python-fastapi/), [FastAPI validation](../web-framework-basics/request-validation/python-fastapi/) |
| `storage-architecture` | Storage semantics, topology, concurrency, or partitioning where storage itself is the primary subject. | Persistence used only as an implementation detail behind application boundaries. | [in-memory key-value store](../in-memory-storage/key-value-store/go-http/), [database sharding](../database-sharding/order-service/java-spring-shardingsphere/) |
| `numeric-precision` | Numeric representation, arithmetic correctness, rounding, or precision tradeoffs. | Domain modeling where numeric behavior is incidental. | [money representations](../numeric-precision/money/go-decimal/) |

## When to create a new concept

Create one only when every statement is true:

- [ ] No existing admission boundary describes the primary hypothesis.
- [ ] The distinction is an engineering concern, not an implementation or packaging detail.
- [ ] The name would remain useful across languages, frameworks, domains, and repository sources.
- [ ] Another experiment could plausibly investigate the same concern.
- [ ] The boundary and exclusions can be stated clearly.

If any statement is false, reuse the closest existing concept and clarify the experiment name or README instead.

## Classification anti-patterns

| Avoid | Why |
| --- | --- |
| `rest-api` | Describes a delivery or interface style; classify the underlying question, such as HTTP contracts or application boundaries. |
| `web-framework-basics` | Groups by implementation context instead of the engineering concern. |
| Language buckets such as `go` or `python` | Languages belong at the implementation level. |
| Framework names such as `express` or `fastapi` | Frameworks are tools used to test a hypothesis, not durable concepts. |
| Business-domain-only buckets such as `weather` or `content-management` | Domains provide examples but do not define the reusable engineering question. |

## Migration status

Current directory paths remain unchanged for now. This guide defines the target classification model; any directory move requires a separate reviewed change that updates affected links and automation.
