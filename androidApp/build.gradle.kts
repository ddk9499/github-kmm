plugins {
    id("com.android.application")
    kotlin("android")
}

val composeVersion = "1.0.0-alpha08"
dependencies {
    implementation(project(":githubSdk"))

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.0-alpha08")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    // Material Design
    implementation("androidx.compose.material:material:$composeVersion")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "uz.dkamaloff.githubkmm.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.20"
        kotlinCompilerExtensionVersion = composeVersion
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}