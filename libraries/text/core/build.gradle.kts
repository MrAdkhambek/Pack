plugins {
    id("adkhambek.kotlin")
}

kotlin {
    explicitApiWarning()
}

java {
//    sourceCompatibility = Config.javaVersion
//    targetCompatibility = Config.javaVersion
}

dependencies {
    implementation(libs.annotation)
}
