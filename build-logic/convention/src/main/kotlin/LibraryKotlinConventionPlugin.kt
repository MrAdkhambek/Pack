@file:Suppress(
    "UnstableApiUsage",
)

import me.adkhambek.app.Config
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class LibraryKotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }

            tasks.withType(KotlinCompile::class).all {
                kotlinOptions {
                    jvmTarget = Config.javaVersion.toString()
                    freeCompilerArgs = (freeCompilerArgs + Config.freeCompilerArgs).distinct()
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", libs.findBundle("test-unit").get())
            }
        }
    }
}
