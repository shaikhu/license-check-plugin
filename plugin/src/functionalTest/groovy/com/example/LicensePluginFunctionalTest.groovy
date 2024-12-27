package com.example

import org.gradle.testkit.runner.GradleRunner
import spock.lang.Specification
import spock.lang.TempDir

class LicensePluginFunctionalTest extends Specification {
    @TempDir
    private File projectDir

    private getBuildFile() {
        new File(projectDir, 'build.gradle')
    }

    private getSettingsFile() {
        new File(projectDir, 'settings.gradle')
    }

    private getLicenseFile() {
        new File(projectDir, 'license.txt')
    }

    def 'can run task'() {
        given:
        licenseFile << '''
/*
 * Licensed under foo bar
 */
'''
        settingsFile << "rootProject.name = 'testProject'"
        buildFile << '''
plugins {
  id('com.example.license')    
}
'''
        when:
        def result = GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments('licenseCheck', '--info')
                .withProjectDir(projectDir)
                .build()

        then:
        result.output.contains('checking license header...')
    }
}
