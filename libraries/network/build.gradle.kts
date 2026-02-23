plugins {
    id("com.adkhambek.android.library")
    id("com.adkhambek.publish")
}

android {
    namespace = "com.adkhambek.pack.network"
    defaultConfig {
        minSdk = 23
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexplicit-api=warning")
    }
}

dependencies {
    implementation(libs.annotation)
    compileOnly(libs.coreKtx)
    compileOnly(libs.kotlin.coroutines.core)
    testImplementation(libs.coreKtx)
    testImplementation(libs.kotlin.coroutines.test)
}
