plugins {
    id("com.adkhambek.android.library")
    id("com.adkhambek.publish")
}

android {
    namespace = "com.adkhambek.sms"
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexplicit-api=warning")
    }
}

dependencies {
    implementation(libs.annotation)
    compileOnly(libs.livedata.core)
    compileOnly(libs.kotlin.coroutines.core)
    compileOnly(libs.gms.play.auth)

    testImplementation(libs.kotlin.coroutines.test)
}
