import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

val libraryVersion = "1.0.0"

group = "com.github.g000sha256"
version = libraryVersion

plugins {
    alias(catalog.plugins.android.library)
    alias(catalog.plugins.jetbrains.kotlin.android)
    id("org.gradle.maven-publish")
}

android {
    buildToolsVersion = "34.0.0"
    compileSdk = 34
    namespace = "g000sha256.material.color_scheme"

    buildTypes {
        release {
            isMinifyEnabled = true

            proguardFile("proguard.pro")

            optimization {
                keepRules { ignoreFromAllExternalDependencies(true) }
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    publishing {
        singleVariant("release") { withSourcesJar() }
    }
}

kotlin {
    jvmToolchain(8)

    compilerOptions {
        jvmTarget = JvmTarget.JVM_1_8
        moduleName = android.namespace
    }

    sourceSets {
        main {
            dependencies {
                api(catalog.jetbrains.annotations) { isTransitive = false }
                api(catalog.jetbrains.kotlin) { isTransitive = false }

                api(catalog.androidx.core) { isTransitive = false }
                api(catalog.compose.material3) { isTransitive = false }
                api(catalog.compose.ui.graphics) { isTransitive = false }
            }
        }

        androidTest {
            dependencies {
                implementation(catalog.test.android.runner)
                implementation(catalog.test.jUnit)
            }
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "g000sha256"
            artifactId = "material.color_scheme"
            version = libraryVersion

            pom {
                name = "Material Color Scheme"
                description = "Dynamic Material 3 color scheme builder for Compose"
                url = "https://github.com/g000sha256/material_color_scheme"

                licenses {
                    license {
                        name = "Apache License 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }

                developers {
                    developer {
                        id = "g000sha256"
                        name = "Georgii Ippolitov"
                        email = "material_color_scheme.github@g0sha.dev"
                        url = "https://github.com/g000sha256"
                    }
                }
            }

            afterEvaluate {
                val component = components["release"]
                from(component)
            }
        }
    }
}

fun KotlinDependencyHandler.api(
    provider: Provider<MinimalExternalModuleDependency>,
    configure: ExternalModuleDependency.() -> Unit
) {
    val dependency = provider
        .get()
        .toString()
    api(dependency, configure)
}