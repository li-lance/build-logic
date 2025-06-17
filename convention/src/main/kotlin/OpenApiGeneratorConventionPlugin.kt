import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.openapitools.generator.gradle.plugin.OpenApiGeneratorPlugin
import org.openapitools.generator.gradle.plugin.extensions.OpenApiGeneratorGenerateExtension

class OpenApiGeneratorConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<OpenApiGeneratorPlugin>()
            extensions.configure<OpenApiGeneratorGenerateExtension> {
                generatorName.set("kotlin")
//                inputSpec.set("${projectDir.path}/openapi/api-pokemon.yml")
                val ymlPath = findProperty("openapiSpecPath") as? String
                    ?: "${projectDir.path}/openapi/official-spotify-open-api.yml"
                inputSpec.set(ymlPath)
                outputDir.set("${layout.buildDirectory.asFile.get().absolutePath}/openapi")
                apiPackage.set("com.seraphim.music.api")
                modelPackage.set("com.seraphim.music.model")
                packageName.set("com.seraphim.music.invoker")
                skipValidateSpec.set(true)
                configOptions.putAll(
                    mapOf(
                        "dateLibrary" to "kotlinx-datetime",
                        "library" to "multiplatform",
                    )
                )
            }

        }
    }
}