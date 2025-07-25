import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    alias(libs.plugins.android.lint)
}
group = "com.seraphim.plugin"
// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.openapi.generator.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.vanniktech.maven.publish.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
    implementation(libs.truth)
    lintChecks(libs.androidx.lint.gradle)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}
gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = libs.plugins.seraphim.android.application.compose.get().pluginId
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.seraphim.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = libs.plugins.seraphim.android.application.jacoco.get().pluginId
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = libs.plugins.seraphim.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.seraphim.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.seraphim.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = libs.plugins.seraphim.android.library.jacoco.get().pluginId
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }
        register("androidTest") {
            id = libs.plugins.seraphim.android.test.get().pluginId
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("hilt") {
            id = libs.plugins.seraphim.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }
        register("koin"){
            id = libs.plugins.seraphim.koin.get().pluginId
            implementationClass = "KoinConventionPlugin"
        }
        register("androidRoom") {
            id = libs.plugins.seraphim.android.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidFirebase") {
            id = libs.plugins.seraphim.android.application.firebase.get().pluginId
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
        register("androidFlavors") {
            id = libs.plugins.seraphim.android.application.flavors.get().pluginId
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        register("androidLint") {
            id = libs.plugins.seraphim.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }
        register("jvmLibrary") {
            id = libs.plugins.seraphim.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("kotlinMultiplatformLibrary") {
            id = libs.plugins.seraphim.kotlin.multiplatform.library.get().pluginId
            implementationClass = "KotlinMultiplatformLibraryConventionPlugin"
        }
        register("openApiGenerator") {
            id = libs.plugins.seraphim.openapi.generator.get().pluginId
            implementationClass = "OpenApiGeneratorConventionPlugin"
        }
        register("springBoot") {
            id = libs.plugins.seraphim.spring.boot.get().pluginId
            implementationClass = "SprintBootConventionPlugin"
        }
    }
}