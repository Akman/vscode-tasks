# VSCode Tasks Gradle Plugin

[![Build Status](https://travis-ci.org/akman/vscode-tasks.svg?branch=master)](https://travis-ci.org/akman/vscode-tasks)
[![Code Coverage](https://codecov.io/gh/akman/vscode-tasks/branch/master/graph/badge.svg)](https://codecov.io/gh/akman/vscode-tasks)
[![License](https://img.shields.io/github/license/akman/vscode-tasks.svg)](https://github.com/akman/vscode-tasks/blob/master/LICENSE)

This plugin scans your gradle project and configures VS Code to be able
to run all gradle tasks from within VS Code. It will create or update
the ***tasks.json*** file in the ***.vscode*** folder for a workspace.

## Applying the plugin

The plugin available through the Gradle plugin exchange and the configuration
for your project is done in the ***build.gradle*** file.

```groovy
plugins {
    id "ru.akman.vscode-tasks" version "0.2.0"
}
```

## Tasks

There is one task available in your project:

- **vscodeTasks** - create or update VS Code configuration

```bash
./gradlew vscodeTasks
```

## Configuration

You can edit the almost all of the task's properties, but following properties
are not configurable and set by the plugin itself:

- group
- label
- detail

Below are shown all configurable task's properties with their default values.
They apply to each task added to the VS Code configuration.

[The VS Code customs tasks documentation is available here][see]

```groovy
vscodeTasks {
    type = 'shell'
    command = './gradlew'
    args = []
    options = [
        'cwd': '',
        'env': [:],
        'shell': [:]
    ]
    problemMatcher = []
    windows = [:]
    linux = [:]
    osx = [:]
    runOptions = [
        'reevaluateOnRerun': true,
        'runOn': 'default'
    ]
    promptOnClose = true
    isBackground = true
    presentation = [
        'group': 'gradle',
        'echo': true,
        'reveal': 'always',
        'revealProblems': 'never',
        'focus': true,
        'panel': 'shared',
        'showReuseMessage': true,
        'clear': false
    ]
    dependsOn = []
    dependsOrder = 'parallel'
}
```

Note that unlike ***json*** syntax, ***groovy*** syntax is used here. Therefore,
do not forget to replace the curly braces (from json) with square brackets in
the maps definitions.

[see]: https://code.visualstudio.com/docs/editor/tasks#_custom-tasks
