plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.notice_app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.notice_app"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // <-- HIER: Room-Schema-Export (Kotlin-DSL, korrekt platziert)
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas"
                )
            }
        }
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

    buildFeatures {
        viewBinding = true
    }

    // Java – passt für Room
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // deine bestehenden Libs (Version Catalog)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Room (Java: annotationProcessor, kein KSP/KAPT)
    val room_version = "2.8.2"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // optional:
    // implementation("androidx.room:room-paging:$room_version")
    // testImplementation("androidx.room:room-testing:$room_version")
}
