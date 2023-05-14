package com.crottv.crotstream.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.crottv.crotstream.gradle.tasks.registerTasks
import com.crottv.crotstream.gradle.configuration.registerConfigurations

abstract class CrotstreamPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("crotstream", CrotstreamExtension::class.java, project)

        registerTasks(project)
        registerConfigurations(project)
    }
}