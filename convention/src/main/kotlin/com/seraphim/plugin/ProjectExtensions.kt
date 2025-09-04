package com.seraphim.plugin

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val Project.targetSdkVersion
    get() = 36
val Project.compileSdkVersion
    get() = 36
val Project.minSdkVersion
    get() = 28