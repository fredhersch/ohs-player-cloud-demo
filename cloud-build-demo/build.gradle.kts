// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// app/build.gradle.kts
dependencies {
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