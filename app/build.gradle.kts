plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.attendify_admin"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.attendify_admin"
        minSdk = 24
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.material)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.hilt.android) // Dagger Hilt for Dependency Injection (DI) in Android
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.constraintlayout)// Hilt integration with Jetpack Compose navigation
    ksp(libs.hilt.android.compiler) // Compiler for Hilt to generate necessary code for DI
    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.core.splashscreen) // Provides the SplashScreen API to show a splash screen on app launch
    implementation(libs.androidx.navigation.compose) // For Compose-based navigation in your app
    implementation(libs.retrofit) // Retrofit for making HTTP requests and handling API responses
    implementation(libs.retrofit.gson) // Gson converter for Retrofit to handle JSON serialization and deserialization
    implementation(libs.coil.compose) // Coil for image loading in Jetpack Compose
    implementation(libs.datastore.preferences) // DataStore for storing key-value pairs asynchronously in a type-safe manner
    implementation(libs.compose.foundation) // Jetpack Compose Foundation library for foundational UI components and features
    implementation(libs.accompanist.systemuicontroller) // Accompanist library to manage system UI (e.g., status bar, navigation bar) in Jetpack Compose
    implementation(libs.paging.runtime) // Paging library for efficient loading and display of large data sets
    implementation(libs.paging.compose) // Jetpack Compose integration with Paging library
    implementation(libs.room.runtime) // Room for local database persistence with SQLite
    ksp(libs.room.compiler) // Room compiler for generating code related to database entities and DAOs
    implementation(libs.room.ktx) // Room KTX for Kotlin extensions to simplify Room database interactions
    implementation(libs.kotlinx.serialization.json) // Kotlin serialization for data serialization and deserialization
    implementation(libs.libphonenumber)

    //predefined
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}