package com.example

import org.gradle.api.Plugin
import org.gradle.api.Project

class LicensePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.register('licenseCheck', LicenseTask) { task ->
            task.description = 'checks source code for a license header'
            task.group = 'from license plugin'
        }
    }
}