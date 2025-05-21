import com.android.build.gradle.LibraryExtension
import com.seraphim.plugin.configureKotlinJvm
import com.seraphim.plugin.configureKotlinMultiplatform
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
                defaultConfig.targetSdk = project.findProperty("targetSdk")?.toString()?.toInt()
                defaultConfig.minSdk = project.findProperty("minSdk")?.toString()?.toInt()
                compileSdk = project.findProperty("compileSdk")?.toString()?.toInt()
                testOptions.animationsDisabled = true
            }

        }
    }
}