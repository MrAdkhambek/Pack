@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.library")
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
