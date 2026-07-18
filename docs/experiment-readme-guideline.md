# Experiment README guideline

Use this structure for new or migrated Forge experiments. Keep the README concise, delete sections that truly do not apply, and link deeper artifacts instead of duplicating them.

## Required experiment record

### Concept

State the engineering concept being tested and why this implementation is a useful example.

### Hypothesis or problem

Describe the question, assumption, or bounded problem. Define what evidence would support or challenge it.

### Architecture

Identify entry points, important boundaries, data flow, dependencies, and external systems. Prefer a short diagram or table when prose becomes difficult to scan.

### Quick path

Give the shortest reproducible setup and execution path. Include prerequisites and commands from the experiment directory.

### Commands

List build, run, test, lint, and cleanup commands. Prefer the experiment's Taskfile when one exists, but always show the underlying tool command where that improves portability.

### Configuration

List required environment variables and configuration files. Commit only placeholders or safe examples. Never commit credentials, tokens, private HTTP environments, or real `.env` files.

### Expected behavior

Describe observable success and relevant failure behavior. Do not claim a command passed unless it was executed against the current snapshot.

### Tradeoffs

Record deliberate simplifications, constraints, security implications, and alternatives that would matter in production.

### Status

Use a precise label such as `active experiment`, `migrated snapshot`, `validated`, or `archived learning`. Add known limitations and the last meaningful verification date when useful.

### Verification

Record exact commands and outcomes. Separate static, build, and runtime evidence, and explicitly identify checks skipped because of credentials, UI access, infrastructure, or platform constraints.

### Agent boundaries

Inherit root `AGENTS.md` by default. Add a local `AGENTS.md` only for constraints unique to the experiment. State boundaries in the README when they help human reviewers too, such as preserving behavior, avoiding external calls, or keeping generated files out of the snapshot.

## Review checklist

- The directory is concept-first, with language or runtime below the concept.
- Setup starts from a clean checkout and does not rely on a private local tool state.
- Commands and expected results agree with the current source.
- Safe examples contain placeholders only.
- Verification claims are backed by recorded commands.
- The experiment can be removed without changing unrelated experiments.
