package com.adkhambek.app

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Configure test options for Android modules
 */
internal fun Project.configureTestAndroid(
    commonExtension: CommonExtension,
) {
    commonExtension.testOptions.apply {
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("testImplementation", libs.findBundle("test-android-unit").get())
        add("androidTestImplementation", libs.findBundle("test-android").get())
    }
}
