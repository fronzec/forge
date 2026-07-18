# Chunk-oriented log aggregation with Spring Batch

## Concept

This migrated snapshot demonstrates a chunk-oriented Spring Batch job that reads CSV log records, enriches each message, and writes records to H2.

## Hypothesis

Spring Batch can isolate file parsing, processing, persistence, and job launch concerns while supporting synchronous and asynchronous HTTP-triggered execution.

## Architecture

`JobLauncherController` creates unique job parameters and selects a launcher. `BatchConfig` wires a classpath CSV reader, a processor that prefixes messages with `[Processed]`, and a JDBC writer in chunks of ten. H2 stores both Spring Batch metadata and imported `log_data` rows.

## Quick path

Prerequisites: Java 21 and Maven 3.6 or newer.

```bash
mvn spring-boot:run
curl -X POST 'http://localhost:8080/api/jobs/import-logs?filePath=input1.csv&sync=true'
```

A successful synchronous request should return job metadata with a completed status. The H2 console is available at `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:mem:logdb`, user `sa`, and an empty password.

## Commands and verification

```bash
mvn test
mvn package
```

Results on 2026-07-18: `mvn test` completed with `BUILD SUCCESS` and reported no tests to run; `mvn package -DskipTests` completed with `BUILD SUCCESS`. The generated `target/` directory was removed after verification. Runtime HTTP verification was skipped because it requires starting the local server; the build exercises application compilation without external services.

## Configuration

`src/main/resources/application.properties` configures an in-memory H2 database and port 8080. It contains no production credentials. Input files must be classpath resources because the reader uses `ClassPathResource`.

## Tradeoffs and status

Status: **migrated snapshot**. The processor deliberately sleeps for 100 ms per item to make execution differences visible. The endpoint exposes arbitrary classpath resource selection and the H2 console, which are learning conveniences rather than production security choices.

The source `api.http` was not migrated because it targeted an unrelated health endpoint on port 3000; the accurate `curl` request above replaces it. Application behavior was not changed.

## Agent boundaries

Preserve the batch-learning scope and simulated processing delay unless an experiment explicitly studies performance. Do not commit H2 database files, Maven `target/`, IDE request history, or generated responses.
