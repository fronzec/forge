# Repository Instructions

Forge is a multilingual engineering lab organized by technology concepts, not programming languages.

- Keep every experiment self-contained, including its dependencies, commands, and context.
- Write technical artifacts in English unless an experiment explicitly requires another language.
- Keep documentation and tests with the behavior they describe.
- Preserve focused scope; do not expand one experiment into unrelated repository-wide infrastructure.
- Use each experiment's README for setup, commands, behavior, and local context.
- Do not assume experiments share a language, runtime, package manager, or toolchain.
- Run the narrowest verification relevant to the changed experiment.
- Treat each directory's `AGENTS.md` as the canonical agent instruction source; edit it when rules change, and keep the corresponding `CLAUDE.md` limited to importing `@AGENTS.md` without duplicating instructions.

## Tooling Preferences

- Prefer Taskfile for repeatable development, verification, and lifecycle commands.
- Prefer `curl` for HTTP request examples in Markdown documentation, and also provide companion `.http` files when practical for reproducible execution.
- Prefer mise for runtime and tool version management.
- Prefer [SDKMAN!](https://sdkman.io/) for Java runtimes and JVM ecosystem tools instead of mise.
- Prefer [fnm](https://github.com/Schniz/fnm) for Node.js version management instead of mise.
- Prefer process-compose when an experiment requires multiple local processes.
- Prefer pipx for installing isolated Python CLI applications.
- Prefer pnpm for Node.js package management.
- Prefer Lefthook for repository-managed Git hooks.
- Prefer `docker-compose.yml` files for Compose workloads and run them with
  [`containers/podman-compose`](https://github.com/containers/podman-compose) via `podman-compose`.
- Do not assume `podman compose` uses `podman-compose`; it delegates to an external provider and may select `docker-compose` when both are installed.
- Prefer [Locust](https://locust.io/) and [k6](https://k6.io/) for load and performance testing, selecting the tool that best fits the experiment.
- Prefer [Mockoon](https://mockoon.com/) or [Mockintosh](https://mockintosh.io/) for service mocks, selecting the tool that best fits the experiment and automation needs.
- Keep tooling proportional; do not introduce infrastructure that a simple experiment does not need.
- Experiment-local requirements and constraints override these defaults.
