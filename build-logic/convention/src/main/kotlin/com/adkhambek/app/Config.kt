package com.adkhambek.app

import org.gradle.api.JavaVersion

internal object Config {
    const val compileSdkVersion = 36

    const val minSdkVersion = 21
    const val targetSdkVersion = 36

    val javaVersion = JavaVersion.VERSION_21

    val freeCompilerArgs = listOf(
        "-opt-in=kotlin.RequiresOptIn",
        "-Xjvm-default=all",
    )
}
