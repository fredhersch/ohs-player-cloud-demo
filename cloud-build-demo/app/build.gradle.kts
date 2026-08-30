plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
}

android {
    namespace = "com.example.ohs_demo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ohs_demo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    packaging {
        resources {
            // The FHIR SDK's transitive dependencies (hapi-fhir, json-patch) ship
            // duplicate license/metadata files under META-INF; only one copy is needed.
            excludes += setOf(
                "META-INF/ASL-2.0.txt",
                "META-INF/LGPL-3.0.txt",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/DEPENDENCIES",
                "META-INF/INDEX.LIST",
                "META-INF/sun-jaxb.episode",
            )
        }
    }
}

configurations.all {
    resolutionStrategy {
        // com.google.android.fhir:data-capture:1.1.0 and :engine:0.1.0-beta05 pull in
        // conflicting strict Jackson version constraints transitively (via hapi-fhir vs
        // json-patch); force a single, mutually compatible version to resolve the deadlock.
        force(
            "com.fasterxml.jackson.core:jackson-databind:2.15.2",
            "com.fasterxml.jackson.core:jackson-core:2.15.2",
            "com.fasterxml.jackson.core:jackson-annotations:2.15.2",
            "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2",
            "com.fasterxml.jackson:jackson-bom:2.15.2",
        )
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Open Health Stack (FHIR SDC & Engine)
    implementation("com.google.android.fhir:data-capture:1.1.0")
    implementation("com.google.android.fhir:engine:0.1.0-beta05")

    // Core Android UI & Lifecycle
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.fragment:fragment-ktx:1.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
}
