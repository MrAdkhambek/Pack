plugins {
    `kotlin-dsl`
}

group = "me.adkhambek.app.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.publish.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("publisher") {
            id = "adkhambek.android.publish"
            implementationClass = "PublisherPlugin"
        }
    }
}