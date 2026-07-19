# Parity-sharded order service with Spring and ShardingSphere

## Concept

This experiment demonstrates database sharding behind a conventional Spring Data JPA application. Apache ShardingSphere JDBC selects one of two MySQL databases without exposing routing logic to the controller, service, or repository.

## Hypothesis

An inline parity rule on `order_id` should route even IDs to `ds0` and odd IDs to `ds1` while preserving the same `POST /orders` and `GET /orders/{id}` API. The hypothesis is supported when API responses succeed and direct SQL inspection finds each order only on its expected shard.

## Architecture

```text
HTTP request
  -> OrderController
  -> OrderService
  -> OrderRepository (Spring Data JPA)
  -> ShardingSphere JDBC (order_id % 2)
  -> ds0/db1 for even IDs | ds1/db2 for odd IDs
```

`application.yml` activates the ShardingSphere JDBC driver. `sharding.yml` defines the physical data sources and the `ds${order_id % 2}` inline routing rule. Hibernate creates and drops the logical `order` table for each application run.

## Quick path

Prerequisites: Java 17, Podman with a running machine where required, `podman-compose`, and Task.

```bash
task setup
# Wait until both MySQL health checks report healthy.
task run
```

In another terminal, execute `requests.http` or send the odd/even examples with `curl`:

```bash
curl --request POST http://localhost:8080/orders \
  --header 'Content-Type: application/json' \
  --data '{"orderId":1,"customerId":1,"totalPrice":1.2,"orderStatus":"CANCELLED","orderDate":"2024-01-01","deliveryAddress":"some address"}'
curl --request POST http://localhost:8080/orders \
  --header 'Content-Type: application/json' \
  --data '{"orderId":2,"customerId":1,"totalPrice":1.2,"orderStatus":"CANCELLED","orderDate":"2024-01-01","deliveryAddress":"some address"}'
```

## Commands

| Task | Underlying command | Purpose |
| --- | --- | --- |
| `task setup` | `podman-compose --file docker-compose.yml up --detach` | Create `.env` and start both shards. |
| `task run` | `./mvnw spring-boot:run` | Run the service with variables exported from `.env`. |
| `task test` | `./mvnw test` | Run direct service/controller unit tests without MySQL. |
| `task verify` | `podman-compose ... config` and `./mvnw verify` | Validate local configuration and the Maven build. |
| `task cleanup` | `podman-compose ... down --volumes`, `./mvnw clean` | Remove local runtime and build state. |

## Configuration

Copy `.env.example` to `.env`; `task setup` does this without overwriting an existing file. ShardingSphere resolves `$${NAME::default}` entries in `sharding.yml` because the application JDBC URL enables `placeholder-type=environment`.

| Variables | Development defaults | Purpose |
| --- | --- | --- |
| `DB1_HOST`, `DB1_PORT`, `DB1_NAME` | `localhost`, `3306`, `db1` | Even-order shard location. |
| `DB2_HOST`, `DB2_PORT`, `DB2_NAME` | `localhost`, `3307`, `db2` | Odd-order shard location. |
| `DB1_USERNAME`, `DB2_USERNAME` | `root` | Local MySQL users. |
| `DB1_PASSWORD`, `DB2_PASSWORD` | `root` | Local MySQL passwords. |

The committed `root`/`root` values are DEVELOPMENT-ONLY credentials for isolated local containers. Replace them for any shared or exposed environment; `.env` is ignored.

## Expected behavior

- `POST /orders` returns the persisted order with HTTP 200.
- `GET /orders/{id}` returns an existing order with HTTP 200.
- A missing ID raises the snapshot's `IllegalArgumentException("Order not found")` behavior.
- Even `order_id` values route to `ds0` (`db1`, host port 3306).
- Odd `order_id` values route to `ds1` (`db2`, host port 3307).

After posting IDs 1 and 2, prove physical routing directly:

```bash
set -a; . ./.env; set +a
podman-compose --file docker-compose.yml exec -T db1 \
  mysql -uroot "-p${DB1_PASSWORD}" -D "${DB1_NAME}" \
  -e 'SELECT order_id FROM `order` ORDER BY order_id;'
podman-compose --file docker-compose.yml exec -T db2 \
  mysql -uroot "-p${DB2_PASSWORD}" -D "${DB2_NAME}" \
  -e 'SELECT order_id FROM `order` ORDER BY order_id;'
```

The first query should contain only ID 2; the second should contain only ID 1. Run the SQL while the application is running because `create-drop` removes the tables at shutdown.

## Tradeoffs

- `spring.jpa.hibernate.ddl-auto=create-drop` makes repeated experiments disposable but destroys data and is unsuitable for production.
- Fixed default host ports 3306 and 3307 simplify the routing proof but can collide with existing MySQL instances.
- Local `root` credentials reduce setup friction but are intentionally unsafe outside an isolated development machine.
- `order` is reserved in MySQL, so direct SQL must quote the table name with backticks.
- Spring Boot 3.1.5, ShardingSphere JDBC 5.3.2, MySQL 8.0.23, and the Maven wrapper are preserved historical snapshot versions, not upgrade recommendations.
- The Compose file does not force an architecture, allowing the image runtime to select an ARM64 or x86_64 variant when available.

## Status

**Migrated snapshot.** Migrated from `fronzec/spring-projects` commit `c72d2e7ac4e041e61d4f8602f9b494b74ed050fa`. Snapshot behavior is preserved except for portable environment configuration, the current MySQL driver class, a reproducible local harness, and focused infrastructure-independent tests.

## Verification

Verification on 2026-07-18:

- Static: `git diff --check` and `xmllint --noout pom.xml` passed. Ruby Psych parsed `application.yml`, `sharding.yml`, `docker-compose.yml`, and `Taskfile.yml`; Compose-specific validation could not run because the installed `podman-compose` pyenv shim has no executable in the active Python version.
- Build/unit: `./mvnw test` and `./mvnw verify` passed with 4 tests, 0 failures, 0 errors, and 0 skipped; the latter produced the executable JAR before cleanup.
- Runtime: `podman info` confirmed Podman 5.8.2 on Darwin ARM64 but could not connect because the Podman machine was stopped. No machine was started and no container, HTTP, or physical routing proof is claimed.

The unit tests exercise service and controller behavior directly and deliberately do not load the Spring context or connect to MySQL. The HTTP/SQL sequence above is the exact manual routing proof because automating the complete process reliably would require a dedicated orchestration script beyond this experiment's scope.

## Agent boundaries

Inherit the repository-root `AGENTS.md`. Preserve the historical framework versions, package `com.fronzec.shaordser`, API behavior, and parity rule; do not broaden this snapshot into production architecture. Keep `.env`, Maven `target/`, database volumes, IDE metadata, and other generated state out of version control.
