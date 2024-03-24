pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/si-pramod-singh/icc-fantasy")
            credentials {
                username = System.getProperty("gpr.uefa.user") ?: System.getenv("GPR_UEFA_USER")
                password = System.getProperty("gpr.uefa.key") ?: System.getenv("GPR_UEFA_KEY")
            }
        }
    }
}

rootProject.name = "FantasyHostApp"
include(":app")
include(":iccfantasy")
