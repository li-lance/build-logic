import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class VMavenConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.vanniktech.maven.publish")
//            configureVMaven()
        }
    }
}

//internal fun Project.configureVMaven() {
//    extensions.configure<MavenPublishBaseExtension> {
//        publishToMavenCentral("")
//        coordinates("com.seraphim", project.name)
//        signAllPublications()
//        pom {
//
//        }
//    }
//}