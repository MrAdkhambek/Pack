@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.library")
    id("com.adkhambek.publish")
}

android {
    namespace = "com.adkhambek.pack.text"
    kotlinOptions {
        freeCompilerArgs = (freeCompilerArgs + listOf("-Xexplicit-api=warning")).distinct()
    }
}

dependencies {
    implementation(libs.annotation)
    implementation(libs.fragmentKtx)
    api(projects.libraries.text.core)
}
