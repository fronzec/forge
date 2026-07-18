# Event-driven desktop UI with Qt Widgets

## Concept

This migrated snapshot demonstrates imperative widget composition and Qt's signal-slot event model through PySide6.

## Hypothesis

A small widget tree makes the event flow explicit: a button emits `clicked`, the `magic` slot selects a greeting, and the label renders the new state.

## Architecture

`widget.py` owns application lifecycle. `hello_world.py` defines `MyWidget`, its vertical layout, and the signal-slot connection.

## Quick path

```bash
python3 -m venv .venv
. .venv/bin/activate
python -m pip install -r requirements.txt
python widget.py
```

Clicking **Click me!** should replace “Hello World” with one of four multilingual greetings.

## Commands and verification

```bash
python -m py_compile widget.py hello_world.py
python widget.py
```

Result on 2026-07-18: `python3 -m py_compile widget.py hello_world.py` passed with no output. The GUI runtime check was skipped because it requires PySide6 installation and an interactive display; dependencies were not installed in the worktree.

## Configuration

No secrets or runtime configuration are required.

## Tradeoffs and status

Status: **migrated snapshot**. The example favors direct widget construction over UI files or a model/view split, and `PySide6` is intentionally unpinned as in the source snapshot.

## Agent boundaries

Preserve the signal-slot learning scope and source behavior. Do not add generated IDE metadata, virtual environments, or platform build output.
