import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for publishing to GitHub Packages.
 *
 * Requires in ~/.gradle/gradle.properties:
 *   GITHUB_PACKAGES_USER=your_github_username
 *   GITHUB_PACKAGES_TOKEN=your_personal_access_token (scope: write:packages)
 *
 * The Android Gradle Plugin (com.android.library) automatically creates
 * Maven publications for release variants. This plugin adds the GitHub
 * Packages repository so that `./gradlew publish` uploads artifacts.
 */
class VMavenConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("maven-publish")
            afterEvaluate {
                configureGitHubPackagesRepository()
            }
        }
    }

    private fun Project.configureGitHubPackagesRepository() {
        val githubUser = findProperty("GITHUB_PACKAGES_USER") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_USER").orNull
            ?: "unknown"
        val githubToken = findProperty("GITHUB_PACKAGES_TOKEN") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_TOKEN").orNull
            ?: "unknown"

        val githubOwner = findProperty("GITHUB_PACKAGES_OWNER") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_OWNER").orNull
            ?: "li-lance"
        val githubRepo = findProperty("GITHUB_PACKAGES_REPO") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_REPO").orNull
            ?: projectDir.parentFile.name

        extensions.configure<PublishingExtension> {
            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri("https://maven.pkg.github.com/$githubOwner/$githubRepo")
                    credentials {
                        username = githubUser
                        password = githubToken
                    }
                }
            }
        }
    }
}
