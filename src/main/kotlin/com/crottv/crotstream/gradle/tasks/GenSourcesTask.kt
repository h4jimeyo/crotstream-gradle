package com.crottv.crotstream.gradle.tasks

import com.crottv.crotstream.gradle.getCrotstream
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.util.function.Function
import java.net.URL
import com.crottv.crotstream.gradle.download
import com.crottv.crotstream.gradle.createProgressLogger

abstract class GenSourcesTask : DefaultTask() {
    @TaskAction
    fun genSources() {
        val extension = project.extensions.getCrotstream()
        val apkinfo = extension.apkinfo!!

        val sourcesJarFile = apkinfo.cache.resolve("crotstream-sources.jar")

        val url = URL("${apkinfo.urlPrefix}app-sources.jar")

        url.download(sourcesJarFile, createProgressLogger(project, "Download sources"))
    }
}