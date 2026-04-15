plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.visin"
    compileSdk = 36 // Ojo: asegúrate de tener instalada la versión 36 en el SDK Manager

    defaultConfig {
        applicationId = "com.example.visin"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Firebase BoM (Control de versiones automático)
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    // Librerías de Firebase
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-storage:20.3.0")

    // --- ESTO ES LO QUE NECESITABAS PARA EL LOGIN DE GOOGLE ---
    implementation("com.google.android.gms:play-services-auth:21.1.1")

    // Librerías base usando el sistema de "libs" (corregido)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Tests (Cambiados a la forma directa para que no te den error de "unresolved")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}