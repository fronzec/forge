# Money Precision with Go Decimal Libraries

## Concept

This experiment demonstrates why binary floating point is unsafe for money and compares rational, accounting, and decimal representations.

## Hypothesis or problem

Financial calculations need explicit decimal precision and rounding semantics. The examples support that claim by contrasting accumulated `float64` error and mutable `math/big` values with decimal-oriented libraries.

## Architecture

| File | Role |
| --- | --- |
| `issues.go` | Demonstrates floating-point and mutable `math/big` pitfalls. |
| `leekchan_accounting.go` | Exercises accounting-style money values. |
| `shopspring_decimal.go` | Exercises arbitrary-precision decimal operations. |
| `main.go` | Runs the demonstrations in sequence. |

## Quick path

Prerequisite: Go 1.26.

```sh
go mod download
go run .
```

## Commands

```sh
go test ./...
go build ./...
go run .
```

## Configuration

No environment variables, external services, or credentials are required.

## Expected behavior

The program prints imprecise floating-point accumulation, rational-number behavior, a `big.Int` aliasing pitfall, and results from the accounting and decimal packages. The unit test checks only the unrelated integer `Sum` helper.

## Tradeoffs

This is a demonstration rather than a domain money type. It does not define currency, scale, rounding policy, serialization, or overflow/error handling, all of which must be explicit in production.

## Status

**Migrated snapshot.** The examples are retained for precision learning, not as a reusable financial library.

## Verification

Verified on 2026-07-18:

- `gofmt -l .` returned no files after formatting the migrated snapshot.
- `go test ./...` passed (`ok secmoney`).
- `go build ./...` passed.
- `go vet ./...` passed.
- `go run .` completed and printed the floating-point, rational, accounting, and decimal demonstrations.

## Agent boundaries

Inherit the root `AGENTS.md`. Keep comparisons educational, do not present a package as universally correct, and avoid generated binaries.
