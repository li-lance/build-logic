package com.seraphim.plugin

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform() {
    configure<KotlinMultiplatformExtension> {
        jvmToolchain(21)
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        sourceSets.commonMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
            }
        }
        compilerOptions {
            freeCompilerArgs.add("-Xexpect-actual-classes")
        }
    }
}