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
                inputSpec.set("${projectDir.path}/openapi/api-pokemon.yml")
                outputDir.set("${layout.buildDirectory.asFile.get().absolutePath}/openapi")
                apiPackage.set("com.seraphim.pokemon.api")
                modelPackage.set("com.seraphim.pokemon.model")
                packageName.set("com.seraphim.pokemon.invoker")
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