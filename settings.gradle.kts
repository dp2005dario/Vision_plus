pluginManagement {
    repositories {
        google() // Simplificado para que encuentre cualquier paquete de Google
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Visión+"
include(":app")