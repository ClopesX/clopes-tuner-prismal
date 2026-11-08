plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.clopes.tuner"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.clopes.tuner.prismal"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.2.0-prismal"
    }

    buildTypes {
        release { isMinifyEnabled = false }
        debug { isMinifyEnabled = false }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions { jvmTarget = "17" }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    // AGP 8.7.3 nao aceita androidx.core 1.17 (exige AGP 8.9+)
    configurations.all {
        resolutionStrategy {
            force("androidx.core:core:1.13.1")
            force("androidx.core:core-ktx:1.13.1")
        }
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")

    // vidro liquido real (OpenGL ES) - MIT
    implementation("com.github.styropyr0:Prismal:1.0.8")

    // base que o Prismal declara; garantidas explicitamente
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2024.09.03"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.runtime:runtime")
}


