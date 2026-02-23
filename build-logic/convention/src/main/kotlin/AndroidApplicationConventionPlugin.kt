import com.adkhambek.app.Config
import com.adkhambek.app.configureKotlinAndroid
import com.adkhambek.app.configureTestAndroid
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureTestAndroid(this)
                defaultConfig.targetSdk = Config.targetSdkVersion
            }
        }
    }
}
