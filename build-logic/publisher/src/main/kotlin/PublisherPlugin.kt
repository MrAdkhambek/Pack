import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SourcesJar
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class PublisherPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.vanniktech.maven.publish")

            val isAndroid = pluginManager.hasPlugin("com.android.library")
            configure<MavenPublishBaseExtension> {
                if (isAndroid) {
                    configure(
                        AndroidSingleVariantLibrary(
                            variant = "release",
                            sourcesJar = SourcesJar.Sources(),
                            javadocJar = JavadocJar.Empty(),
                        )
                    )
                } else {
                    configure(
                        KotlinJvm(
                            javadocJar = JavadocJar.Empty(),
                            sourcesJar = SourcesJar.Sources(),
                        )
                    )
                }
            }
        }
    }
}
