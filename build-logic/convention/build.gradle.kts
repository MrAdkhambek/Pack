plugins {
    `kotlin-dsl`
}

group = "com.adkhambek.app.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.parcelize.gradlePlugin)
    implementation(libs.google.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.adkhambek.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.adkhambek.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("kotlinLibrary") {
            id = "com.adkhambek.kotlin"
            implementationClass = "LibraryKotlinConventionPlugin"
        }
    }
}
