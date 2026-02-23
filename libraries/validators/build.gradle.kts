plugins {
    id("com.adkhambek.kotlin")
    id("com.adkhambek.publish")
}

kotlin {
    explicitApiWarning()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {
    implementation(libs.annotation)
}
