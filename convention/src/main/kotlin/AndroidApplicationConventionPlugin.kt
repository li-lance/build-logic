import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.seraphim.plugin.configureBadgingTasks
import com.seraphim.plugin.configureKotlinAndroid
import com.seraphim.plugin.configurePrintApksTask
import com.seraphim.plugin.targetSdkVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "seraphim.android.lint")
//            apply(plugin = "com.dropbox.dependency-guard")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = project.targetSdkVersion
                testOptions.animationsDisabled = true
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
                configureBadgingTasks(this)
            }
        }
    }
}
