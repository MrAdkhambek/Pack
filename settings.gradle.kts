@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}

rootProject.name = "Pack"
//include(":app")

include(
    ":libraries:text:core",
    ":libraries:text:ktx",

    ":libraries:validators",

    ":libraries:network",

    ":libraries:sms:core",
    ":libraries:sms:debug"
)

