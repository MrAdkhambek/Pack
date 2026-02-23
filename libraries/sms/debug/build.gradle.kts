plugins {
    id("com.adkhambek.android.library")
}

android {
    namespace = "com.adkhambek.sms.debug"
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
    testImplementation(libs.kotlin.coroutines.test)
}
