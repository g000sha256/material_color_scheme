import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import proguard.gradle.ProGuardTask

val libraryGroup = "com.github.g000sha256"
val libraryModule = "material_color_scheme"
val libraryVersion = "1.1.0"

group = libraryGroup
version = libraryVersion

buildscript {
    dependencies { classpath(catalog.plugin.proguard) }
}

plugins {
    alias(catalog.plugins.jetbrains.kotlin.jvm)
    id("org.gradle.maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)

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
originalJarTaskProvider.configure { archiveClassifier = "compiled" }

val minifyTaskProvider = tasks.register<ProGuardTask>("minify") {
    group = "build"
    dependsOn(originalJarTaskProvider)

    val javaHomePath = System.getProperty("java.home")
    libraryjars("$javaHomePath/jmods/java.base.jmod")

    val classpathFiles = mutableSetOf<File>()
    val compileClasspathConfiguration = configurations.compileClasspath.get()
    classpathFiles += compileClasspathConfiguration.resolve()
    val runtimeClasspathConfiguration = configurations.runtimeClasspath.get()
    classpathFiles += runtimeClasspathConfiguration.resolve()
    libraryjars(classpathFiles)

    injars(originalJarTaskProvider)
    outjars("build/libs/$libraryModule-$libraryVersion-minified.jar")

    configuration("proguard.pro")
    printconfiguration("build/proguard/configuration.txt")
    printmapping("build/proguard/mapping.txt")
    printseeds("build/proguard/seeds.txt")
    printusage("build/proguard/usage.txt")
}

val sourcesJarTaskProvider = tasks.kotlinSourcesJar

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

                scm {
                    connection = "scm:git:git://github.com/g000sha256/material_color_scheme.git"
                    developerConnection = "scm:git:ssh://github.com/g000sha256/material_color_scheme.git"
                    url = "https://github.com/g000sha256/material_color_scheme"
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

            val minifyTask = minifyTaskProvider.get()
            val minifyJar = minifyTask.outJarFiles.single()
            artifact(minifyJar) { builtBy(minifyTaskProvider) }

            artifact(sourcesJarTaskProvider) { builtBy(sourcesJarTaskProvider) }
        }
    }
}