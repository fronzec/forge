# Declarative desktop UI with Qt Quick

## Concept

This migrated snapshot separates a declarative QML view from the minimal PySide6 bootstrap that loads it.

## Hypothesis

QML can keep layout and interaction declarations out of the host-language entry point while Python owns application lifecycle and load failure handling.

## Architecture

`main.py` creates `QGuiApplication`, loads `main.qml`, and exits if no root object was created. The QML `ApplicationWindow` owns layout, display state, and the button interaction.

## Quick path

```bash
python3 -m venv .venv
. .venv/bin/activate
python -m pip install -r requirements.txt
python main.py
```

Clicking **Click me** should replace “Hello World” with a random digit from 1 through 4.

## Commands and verification

```bash
python -m py_compile main.py
python main.py
```

Result on 2026-07-18: `python3 -m py_compile main.py` passed with no output. The GUI runtime check was skipped because it requires PySide6 installation and an interactive display; dependencies were not installed in the worktree.

## Configuration

No secrets or runtime configuration are required.

## Tradeoffs and status

Status: **migrated snapshot**. The unpinned `PySide6` dependency favors a small learning example over reproducible dependency resolution. There are no automated QML interaction tests.

## Agent boundaries

Preserve the Python/QML responsibility split and source behavior. Do not add generated Qt Creator files, virtual environments, or platform build output.
