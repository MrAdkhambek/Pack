@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    // Android plugins
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.lib) apply false

    // Kotlin plugins
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false

    alias(libs.plugins.publish)
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
