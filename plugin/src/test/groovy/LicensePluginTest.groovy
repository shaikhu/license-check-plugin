import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class LicensePluginTest extends Specification {
    def 'plugin registers task'() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply('com.example.license')

        then:
        project.tasks.named('licenseCheck') != null
    }
}
