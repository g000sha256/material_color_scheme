import g000sha256.sonatype_maven_central.SonatypeMavenCentralType
import g000sha256.sonatype_maven_central.sonatypeMavenCentralRepository
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val libraryGroup = "dev.g000sha256"
val libraryModule = "material-color-scheme"
val libraryVersion = "1.2.0"

buildscript {
    dependencies { classpath(catalog.plugin.sonatype) }
}

plugins {
    alias(catalog.plugins.jetbrains.dokka)
    alias(catalog.plugins.jetbrains.kotlin.jvm)
    id("org.gradle.maven-publish")
    id("org.gradle.signing")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_1_8
        moduleName = "g000sha256.material.color_scheme"
    }

    sourceSets {
        main {
            dependencies {
                implementation(catalog.library.jetbrains.annotations)
                implementation(catalog.library.jetbrains.kotlin)

                implementation(catalog.library.compose.material3)
                implementation(catalog.library.compose.ui.graphics)
            }
        }

        test {
            dependencies { implementation(catalog.test.jUnit) }
        }
    }
}

sourceSets {
    main { java.srcDirs("src/material-color-utilities/java") }
}

val originalJarTaskProvider = tasks.jar

val sourcesJarTaskProvider = tasks.kotlinSourcesJar

val dokkaJavaDocTaskProvider = tasks.dokkaJavadoc

val dokkaJavaDocJarTaskProvider = tasks.register<Jar>("dokkaJavaDocJar") {
    archiveClassifier = "javadoc"
    group = "documentation"
    dependsOn(dokkaJavaDocTaskProvider)

    val dokkaJavaDocTask = dokkaJavaDocTaskProvider.get()
    from(dokkaJavaDocTask.outputDirectory)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = libraryGroup
            artifactId = libraryModule
            version = libraryVersion

            pom {
                name = "Material Color Scheme"
                description = "Dynamic Material 3 color scheme builder for Compose"
                url = "https://github.com/g000sha256/material_color_scheme"
                inceptionYear = "2024"

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
                        email = "detmmpmznb@g000sha256.dev"
                        url = "https://github.com/g000sha256"
                    }
                }

                scm {
                    connection = "scm:git:git://github.com/g000sha256/material_color_scheme.git"
                    developerConnection = "scm:git:ssh://github.com:g000sha256/material_color_scheme.git"
                    url = "https://github.com/g000sha256/material_color_scheme/tree/master"
                }

                issueManagement {
                    system = "GitHub Issues"
                    url = "https://github.com/g000sha256/material_color_scheme/issues"
                }

                withXml {
                    val rootNode = asNode()
                    val dependenciesNode = rootNode.appendNode("dependencies")
                    val configuration = configurations.implementation.get()
                    configuration.dependencies.forEach { dependency ->
                        val dependencyNode = dependenciesNode.appendNode("dependency")
                        dependencyNode.appendNode("groupId", dependency.group)
                        dependencyNode.appendNode("artifactId", dependency.name)
                        dependencyNode.appendNode("version", dependency.version)
                        dependencyNode.appendNode("scope", "runtime")
                    }
                }
            }

            artifact(originalJarTaskProvider)
            artifact(sourcesJarTaskProvider)
            artifact(dokkaJavaDocJarTaskProvider)
        }
    }
}

signing {
    val key = getProperty("Signing.Key") ?: getEnvironment("SIGNING_KEY")
    val password = getProperty("Signing.Password") ?: getEnvironment("SIGNING_PASSWORD")
    useInMemoryPgpKeys(key, password)

    val publication = publishing.publications["release"]
    sign(publication)
}

sonatypeMavenCentralRepository {
    type = SonatypeMavenCentralType.Manual

    credentials {
        username = getProperty("SonatypeMavenCentral.Username") ?: getEnvironment("SONATYPE_USERNAME")
        password = getProperty("SonatypeMavenCentral.Password") ?: getEnvironment("SONATYPE_PASSWORD")
    }
}

private fun getProperty(key: String): String? {
    return properties[key] as String?
}

private fun getEnvironment(key: String): String? {
    return System.getenv(key)
}