import com.android.build.gradle.LibraryExtension
import com.seraphim.plugin.compileSdkVersion
import com.seraphim.plugin.configureKotlinJvm
import com.seraphim.plugin.configureKotlinMultiplatform
import com.seraphim.plugin.minSdkVersion
import com.seraphim.plugin.targetSdkVersion
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.multiplatform")
            configureKotlinMultiplatform()
            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = project.targetSdkVersion
                defaultConfig.minSdk = project.minSdkVersion
                compileSdk = project.compileSdkVersion
                testOptions.animationsDisabled = true
            }

        }
    }
}