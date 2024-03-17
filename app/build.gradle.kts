@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.skybit.pokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.skybit.pokedex"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    configurations.all {
        resolutionStrategy {
            force("androidx.navigation:navigation-compose:2.7.7")
        }
    }
    // ***** Core *****
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splash.screen)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // ***** Compose *****
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    debugImplementation(libs.ui.tooling)

    // ***** Test *****
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    testImplementation(libs.kotlinx.coroutines.test)
    debugImplementation(libs.ui.test.manifest)

    // ****** Dependency Injection *****
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    ksp(libs.androidx.hilt.compiler)

    // ***** Network *****
    implementation(libs.square.retrofit)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.okhttp.logging.interceptor)

    // ***** Moshi *****
    implementation(libs.square.moshi)
    ksp(libs.kotlin.moshi.codegen)

    // ***** Room *****
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    // ***** Collections *****
    implementation(libs.kotlin.immutable.collections)

    // ***** Image *****
    implementation(libs.coil.compose)
}
