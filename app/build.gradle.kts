@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.whoisthat.pokemon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.whoisthat.pokemon"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(mapOf("path" to ":presenter")))
    implementation(project(mapOf("path" to ":remote")))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":ui")))
    implementation(project(mapOf("path" to ":core")))
    implementation(project(mapOf("path" to ":local")))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit.logging)
    implementation(libs.gson)

    implementation(libs.room.core)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.timber.core)
}