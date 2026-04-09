plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Sin alias, sin versión
}

android {
    namespace = "com.example.visin"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.visin"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    // ... resto de tu bloque android igual
}

dependencies {
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    // Tus otras librerías
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // ... resto de tests igual
}