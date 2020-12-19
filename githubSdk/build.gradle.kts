import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.codingfeline.buildkonfig")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "githubSdk"
            }
        }
    }
    sourceSets {
        val ktorVersion = "1.4.3"
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-auth:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                // Logging
                implementation("com.github.aakira:napier:1.5.0-alpha1")

                implementation("com.russhwolf:multiplatform-settings:0.6.3")
            }
        }
        val androidMain by getting {
            dependencies {
                //Network
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val iosMain by getting {
            dependencies {
                //Network
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }

        val commonTest by getting
        val androidTest by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
    }
}
buildkonfig {
    packageName = "uz.dkamaloff.githubkmm.githubSdk"

    val props = gradleLocalProperties(rootDir)
    defaultConfigs {
        buildConfigField(STRING, "clientId", props.getProperty("CLIENT_ID"))
        buildConfigField(STRING, "clientSecret", props.getProperty("CLIENT_SECRET"))
        buildConfigField(STRING, "redirectUri", props.getProperty("REDIRECT_URI"))
        buildConfigField(STRING, "scope", props.getProperty("SCOPE"))
        buildConfigField(STRING, "state", props.getProperty("STATE_COMMON"))
    }
    targetConfigs(closureOf<NamedDomainObjectContainer<TargetConfigDsl>> {
        create("android") {
            buildConfigField(STRING, "state", props.getProperty("STATE_ANDROID"))
        }
        create("iosArm64") {
            buildConfigField(STRING, "state", props.getProperty("STATE_IOS"))
        }
        create("iosX64") {
            buildConfigField(STRING, "state", props.getProperty("STATE_IOS"))
        }
    })
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework =
        kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)
