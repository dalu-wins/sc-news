# Project Structure

## Packages

Top level packages include "app", "feature" & "core".

![Package Diagram](diagrams/packages.svg)

| **app** | **features** | **core** |
|----------|--------------|----------|
| `app activity`<br>`top-level presentation`<br>`navigation setup` | `presentation`<br>`domain`<br>`data` | `dependency injection (Hilt)`<br>`theme variables` |

## Features

Each feature contains a "presentation", "domain" & "data" layer.

![Feature Diagram](diagrams/feature-package.svg)

| **presentation** | **domain** | **data** |
|------------------|------------|----------|
| `screen.kt`<br>`viewmodel.kt`<br>`state.kt`<br>`event.kt`<br>`components/` | `use_cases/`<br>`repository/` *(Interface)*<br>`models/` | `repository/` *(Implementation)*<br>`data/` |
