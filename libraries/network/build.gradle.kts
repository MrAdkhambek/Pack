@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("adkhambek.android.library")
    id("adkhambek.android.publish")
}

android {
    kotlinOptions {
        freeCompilerArgs = (freeCompilerArgs + listOf("-Xexplicit-api=warning")).distinct()
    }
}

dependencies {
    implementation(libs.annotation)
    compileOnly(libs.livedata.core)
    compileOnly(libs.kotlin.coroutines.core)
    testImplementation(libs.kotlin.coroutines.test)
}
