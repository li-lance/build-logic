import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

/**
 * Convention plugin for publishing to GitHub Packages.
 *
 * Creates Maven publications for Android library modules and uploads them
 * to the GitHub Packages repository.
 *
 * Requires in ~/.gradle/gradle.properties:
 *   GITHUB_PACKAGES_USER=your_github_username
 *   GITHUB_PACKAGES_TOKEN=your_personal_access_token (scope: write:packages)
 *
 * Optional:
 *   GITHUB_PACKAGES_OWNER=github_org_or_user  (default: li-lance)
 *   GITHUB_PACKAGES_REPO=repository_name       (default: autodetected)
 */
class VMavenConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("maven-publish")

            afterEvaluate {
                configurePublication()
                configureRepository()
            }
        }
    }

    private fun Project.configurePublication() {
        val pubGroupId = group.toString()
        val pubArtifactId = name.replace(":", "-")

        val pubVersion = (findProperty("PUBLISH_VERSION") as? String)
            ?: providers.gradleProperty("PUBLISH_VERSION").orNull
            ?: "1.0.0"

        extensions.configure<PublishingExtension> {
            publications {
                create<MavenPublication>("release") {
                    groupId = "com.seraphim.map"
                    artifactId = pubArtifactId
                    version = pubVersion

                    from(components.findByName("release") ?: return@create)

                    pom {
                        name.set(pubArtifactId)
                        description.set("Seraphim map abstraction: $pubArtifactId")
                        url.set("https://github.com/li-lance/android-seraphim-map")
                    }
                }
            }
        }
    }

    private fun Project.configureRepository() {
        val githubUser = findProperty("GITHUB_PACKAGES_USER") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_USER").orNull
            ?: return
        val githubToken = findProperty("GITHUB_PACKAGES_TOKEN") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_TOKEN").orNull
            ?: return

        val githubOwner = findProperty("GITHUB_PACKAGES_OWNER") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_OWNER").orNull
            ?: "li-lance"
        val githubRepo = findProperty("GITHUB_PACKAGES_REPO") as? String
            ?: providers.gradleProperty("GITHUB_PACKAGES_REPO").orNull
            ?: "android-seraphim-map"

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
