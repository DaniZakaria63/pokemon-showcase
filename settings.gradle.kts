pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pokemon Showcase"
include(":app")
include(":core")
include(":domain")
include(":presenter")
include(":ui")
include(":remote")
include(":local")
include(":paging")
