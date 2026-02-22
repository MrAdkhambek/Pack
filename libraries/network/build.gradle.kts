@file:Suppress(
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.library")
    id("com.adkhambek.publish")
}

android {
    namespace = "com.adkhambek.pack.network"
    defaultConfig {
        minSdk = 23
    }
    kotlinOptions {
        freeCompilerArgs = (freeCompilerArgs + listOf("-Xexplicit-api=warning")).distinct()
    }
}

dependencies {
    implementation(libs.annotation)
    compileOnly(libs.coreKtx)
    compileOnly(libs.kotlin.coroutines.core)
    testImplementation(libs.coreKtx)
    testImplementation(libs.kotlin.coroutines.test)
}
