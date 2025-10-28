import groovy.json.JsonSlurper
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.daluwi.sc_news"
    compileSdk = 36

    val packageJsonFile = File(rootDir, "package.json")
    val versionFromPackage = if (packageJsonFile.exists()) {
        val json = JsonSlurper().parse(packageJsonFile) as Map<*, *>
        json["version"] as String
    } else "unknown"

    defaultConfig {
        applicationId = "com.daluwi.sc_news"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = versionFromPackage

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    android {
        signingConfigs {
            create("release") {
                storeFile = file("keystore.jks")
                storePassword = System.getenv("APP_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("APP_KEY_ALIAS")
                keyPassword = System.getenv("APP_KEY_PASSWORD")
            }
        }
        buildTypes {
            getByName("release") {
                signingConfig = signingConfigs.getByName("release")
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    // Custom additions
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.material3)
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.androidx.room.ktx)
    implementation(libs.hilt)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.material)
    implementation(libs.material.kolor)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}