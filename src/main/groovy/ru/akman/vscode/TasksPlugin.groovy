/*
    VSCode Tasks Gradle Plugin

    https://github.com/akman/vscode-tasks

    The MIT License (MIT)

    Copyright (C) 2020 Alexander Kapitman <akman.ru@gmail.com>

    Permission is hereby granted, free of charge, to any person obtaining
    a copy of this software and associated documentation files (the "Software"),
    to deal in the Software without restriction, including without limitation
    the rights to use, copy, modify, merge, publish, distribute, sublicense,
    and/or sell copies of the Software, and to permit persons to whom
    the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included
    in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL
    THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
    FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
    DEALINGS IN THE SOFTWARE.
*/
package ru.akman.vscode

import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.transform.CompileDynamic
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.Project
import org.gradle.api.Plugin

/**
 * VSCode Tasks Gradle Plugin.
 */
@CompileDynamic
class TasksPlugin implements Plugin<Project> {

    void apply(Project project) {
        final Logger LOG = Logging.getLogger(TasksPlugin)
        final String TASK_NAME = 'vscodeTasks'
        TasksPluginExtension extension =
            project.extensions.create(TASK_NAME, TasksPluginExtension)
        project.tasks.register(TASK_NAME) {
            group 'IDE'
            description 'Generates VS Code tasks file.'
            doLast {
                LOG.lifecycle('Update tasks file')
                final String VERSION_KEY = 'version'
                final String VERSION_VALUE = '2.0.0'
                final String TASKS_KEY = 'tasks'
                File settingsDir = new File(project.projectDir, '.vscode')
                File tasksFile = new File(settingsDir, 'tasks.json')
                settingsDir.mkdirs()
                JsonSlurper jsonSlurper = new JsonSlurper()
                Map tasksMap =
                    tasksFile.exists() && tasksFile.text.length() ?
                        jsonSlurper.parseText(tasksFile.text) : [:]
                if (!tasksMap.containsKey(VERSION_KEY)) {
                    tasksMap[VERSION_KEY] = VERSION_VALUE
                }
                if (!tasksMap.containsKey(TASKS_KEY)) {
                    tasksMap[TASKS_KEY] = []
                }
                project.tasks.each { projectTask ->
                    String section = projectTask.group ?: 'other'
                    String description = projectTask.description ?: ''
                    String prefix = extension.prefix ?: section + '-'
                    String label = prefix.toLowerCase() + projectTask.name
                    String group = 'none'
                    switch (projectTask.group) {
                        case 'build':
                            group = projectTask.group
                            break
                        case 'verification':
                            group = 'test'
                            break
                    }                    
                    boolean hasTask = tasksMap.tasks.any { task ->
                        task.group == group &&
                        task.label == label &&
                        task.detail == description
                    }
                    if (!hasTask) {
                        LOG.lifecycle('Added task: ' +
                            "${label} [${group}] - ${description}")
                        tasksMap.tasks << [
                            // not configurable by extension
                            'group': group,
                            'label': label,
                            'detail': description,
                            // configurable by extension
                            'type': extension.type,
                            'command': extension.command,
                            'args': extension.args + [ projectTask.name ],
                            'options': extension.options,
                            'problemMatcher': extension.problemMatcher,
                            'windows': extension.windows,
                            'linux': extension.linux,
                            'osx': extension.osx,
                            'runOptions': extension.runOptions,
                            'promptOnClose': extension.promptOnClose,
                            'isBackground': extension.isBackground,
                            'presentation': extension.presentation,
                            'dependsOn': extension.dependsOn,
                            'dependsOrder': extension.dependsOrder,
                        ]
                    }
                }
                JsonOutput jsonOutput = new JsonOutput()
                tasksFile.text =
                    jsonOutput.prettyPrint(jsonOutput.toJson(tasksMap))
            }
        }
    }

}
