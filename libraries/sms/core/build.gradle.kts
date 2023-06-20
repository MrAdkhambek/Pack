@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.library")
    id("com.adkhambek.publish")
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
    compileOnly(libs.gms.play.auth)

    testImplementation(libs.kotlin.coroutines.test)
}
