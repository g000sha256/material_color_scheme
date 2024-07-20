pluginManagement {
    repositories { mavenCentral() }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        register("catalog") {
            val file = files("catalog.toml")
            from(file)
        }
    }
}