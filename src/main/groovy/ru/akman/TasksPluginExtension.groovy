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

import groovy.transform.CompileDynamic

/**
 * VSCode Tasks Gradle Plugin Extension.
 *
 * Following settings are not configurable: group, label, detail (set by
 * the plugin internally).
 *
 * See https://code.visualstudio.com/docs/editor/tasks
 */
@CompileDynamic
class TasksPluginExtension {

    String type = 'shell'
    String command = './gradlew'
    List args = []
    Map options = [
        'cwd': '',
        'env': [:],
        /**
         * 'shell': [
         *     'args': [],
         *     'executable': '',
         * ],
         */
        'shell': [:],
    ]
    List problemMatcher = []
    /**
     * Map windows = [
     *     'command': './gradlew',
     *     'args': [],
     *     'options': [
     *         'cwd': '',
     *         'env': [:],
     *         'shell': [
     *             'args': [],
     *             'executable': '',
     *         ],
     *     ]
     *    'problemMatcher': [],
     * ]
     */
    Map windows = [:]
    /**
     * Map linux = [
     *     'command': './gradlew',
     *     'args': [],
     *     'options': [
     *         'cwd': '',
     *         'env': [:],
     *         'shell': [
     *             'args': [],
     *             'executable': '',
     *         ],
     *     ]
     *    'problemMatcher': [],
     * ]
     */
    Map linux = [:]
    /**
     * Map osx = [
     *     'command': './gradlew',
     *     'args': [],
     *     'options': [
     *         'cwd': '',
     *         'env': [:],
     *         'shell': [
     *             'args': [],
     *             'executable': '',
     *         ],
     *     ]
     *    'problemMatcher': [],
     * ]
     */
    Map osx = [:]
    Map runOptions = [
        'reevaluateOnRerun': true,
        'runOn': 'default',
    ]
    boolean promptOnClose = true
    boolean isBackground = true
    Map presentation = [
        'group': 'gradle',
        'echo': true,
        'reveal': 'always',
        'revealProblems': 'never',
        'focus': true,
        'panel': 'shared',
        'showReuseMessage': true,
        'clear': false,
    ]
    List dependsOn = []
    String dependsOrder = 'parallel'

}
