import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import java.io.ByteArrayOutputStream
import com.android.build.gradle.*
import com.android.build.api.dsl.DefaultConfig
import com.android.build.gradle.internal.dsl.*

fun Project.kapt(path: Any) {
    configurations["kapt"].dependencies.add(project.dependencies.create(path))
}

fun Project.ksp(path: Any) {
    configurations["ksp"].dependencies.add(project.dependencies.create(path))
}

val Project.gitBranch: String
    get() {
        val byteOut = ByteArrayOutputStream()
        exec {
            commandLine = "git rev-parse --abbrev-ref HEAD".split(" ")
            standardOutput = byteOut
        }
        return String(byteOut.toByteArray()).trim().replace("/", ":")
    }

val Project.gitDescribe: String
    get() {
        val stdout = ByteArrayOutputStream()
        exec {
            executable("/bin/sh")
            args("-c", "git rev-parse --short HEAD")
            standardOutput = stdout
        }
        return stdout.toString().trim()
    }

val Project.commitList: String
    get() {
        val byteOut = ByteArrayOutputStream()
        exec {
            commandLine = "git log --pretty=format:\"%cn(%cr)-%s\" -n 5".split(" ")
            standardOutput = byteOut
        }
        return String(byteOut.toByteArray()).trim().replace("\n", "\n\t\t").replace("\"", "")
    }

val Project.developer: String
    get() {
        val byteOut = ByteArrayOutputStream()
        exec {
            commandLine = "git log --pretty=format:\"%cn\" -n 1".split(" ")
            standardOutput = byteOut
        }
        return String(byteOut.toByteArray()).trim().replace("/", ":")
    }

fun LibraryExtension.baseDefaultConfig(block : DefaultConfig.() -> Unit = {}) {
    compileSdkPreview = Properties.androidCompileSDK
    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdkPreview = Properties.androidTargetSDK
        block()
    }

}

fun BaseAppModuleExtension.baseDefaultConfig(block : DefaultConfig.() -> Unit = {}) {
    compileSdkPreview = Properties.androidCompileSDK
    defaultConfig {
        minSdk = Properties.androidMinSDK
        targetSdkPreview = Properties.androidTargetSDK
        applicationId = Properties.androidPackageName
        versionCode = Properties.androidAppVersionCode
        versionName = Properties.androidAppVersionName
        block()
    }

}