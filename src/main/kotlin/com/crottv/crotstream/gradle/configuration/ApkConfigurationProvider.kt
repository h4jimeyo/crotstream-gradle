package com.crottv.crotstream.gradle.configuration

import com.crottv.crotstream.gradle.ApkInfo
import com.crottv.crotstream.gradle.createProgressLogger
import com.crottv.crotstream.gradle.download
import com.crottv.crotstream.gradle.getCrotstream
import groovy.json.JsonSlurper
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import java.lang.Integer.parseInt
import java.net.URL
import java.nio.file.Files

class ApkConfigurationProvider : IConfigurationProvider {

    override val name: String
        get() = "apk"

    override fun provide(project: Project, dependency: Dependency) {
        val extension = project.extensions.getCrotstream()
        if (extension.apkinfo == null) {
            extension.apkinfo = ApkInfo(extension, dependency.version ?: "pre-release")
        }
        val apkinfo = extension.apkinfo!!

        apkinfo.cache.mkdirs()

        if (!apkinfo.jarFile.exists()) {
            project.logger.lifecycle("Fetching JAR")

            val url = URL("${apkinfo.urlPrefix}classes.jar")
            url.download(apkinfo.jarFile, createProgressLogger(project, "Download JAR"))
        }

        project.dependencies.add("compileOnly", project.files(apkinfo.jarFile))
    }
}