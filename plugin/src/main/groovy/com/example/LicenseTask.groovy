package com.example

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class LicenseTask extends DefaultTask {
    @Input
    String filename = getProject().getRootProject().getRootDir().toString() + '/license.txt'

    @TaskAction
    void checkLicenseHeader() {
        outputs.println('checking license header...')
        String licenseContents = new File(filename).text

        new File(getProject().getRootDir().toString()).eachFileRecurse { file ->
            String filename = file.getName()
            if (filename.endsWith('.groovy') || filename.endsWith('.java')) {
                String fileContents = file.text
                if (!fileContents.startsWith(licenseContents)) {
                    file.text = licenseContents + '\n' +  fileContents
                }
            }
        }
    }

}