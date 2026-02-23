plugins {
    id("com.adkhambek.android.library")
    id("com.adkhambek.publish")
}

android {
    namespace = "com.adkhambek.pack.text"
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexplicit-api=warning")
    }
}

dependencies {
    implementation(libs.annotation)
    compileOnly(libs.fragmentKtx)
    api(projects.libraries.text.core)
}
