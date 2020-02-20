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
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification
import spock.lang.Shared

/**
 * One specification class per class testing.
 * One test per method
 *   - each unique non-empty constructor counts as a method
 * One test per method that can throw an exception
 *   - because testing logic for exceptions typically doesn't complement
 *     normal testing logic
 * One test iteration per input combination
 */
@CompileDynamic
class TasksPluginTest extends Specification {

    /**
     * To share an object between feature methods declare a @Shared field.
     *
     * Again it’s best to initialize the field right at the point of
     * declaration.
     * Semantically, this is equivalent to initializing the field at the very
     * beginning of the setupSpec() method.
     * Note that setupSpec() and cleanupSpec() may not reference
     * instance fields UNLESS they are annotated with @Shared.
     */
    @Shared
    Project project = ProjectBuilder.builder().build()

    /**
     * Instance fields are a good place to store objects belonging to
     * the specification’s fixture. It is good practice to initialize them
     * right at the point of declaration.
     * Semantically, this is equivalent to initializing them at the very
     * beginning of the setup() method.
     * Objects stored into instance fields are NOT SHARED between feature
     * methods. Instead, every feature method gets its own object.
     * This helps to isolate feature methods from each other, which is often
     * a desirable goal.
     */

    /**
     * Static fields should only be used for constants.
     * Otherwise shared fields are preferable, because their semantics with
     * respect to sharing are more well-defined.
     */

    /**
     * Runs once -  before the FIRST feature method.
     */
    def setupSpec() {
        project.plugins.apply('ru.akman.vscode-tasks')
    }

    /**
     * Runs before EVERY feature method.
     */
    // def setup() {
    // }

    def "plugin registers task 'vscodeTasks'"() {
        expect:

        project.tasks.findByName('vscodeTasks') != null
    }

    /**
     * Runs after EVERY feature method.
     */
    // def cleanup() {
    // }

    /**
     * Runs once -  after the LAST feature method.
     */
    // def cleanupSpec() {
    // }

}
