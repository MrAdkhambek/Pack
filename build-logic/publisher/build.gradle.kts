plugins {
    `kotlin-dsl`
}

group = "me.adkhambek.app.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.publish.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("publisher") {
            id = "com.adkhambek.publish"
            implementationClass = "PublisherPlugin"
        }
    }
}
