import g000sha256.sonatype_maven_central.SonatypeMavenCentralType
import g000sha256.sonatype_maven_central.sonatypeMavenCentralRepository
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

val projectName = name

val libraryGroup = "dev.g000sha256"
val libraryModule = "material-color-scheme"
val libraryVersion = "1.3.0"

buildscript {
    dependencies { classpath(catalog.plugin.sonatype) }
}

plugins {
    alias(catalog.plugins.android.library)
    alias(catalog.plugins.jetbrains.dokka)
    alias(catalog.plugins.jetbrains.kotlin.binaryCompatibilityValidator)
    alias(catalog.plugins.jetbrains.kotlin.multiplatform)
    id("org.gradle.maven-publish")
    id("org.gradle.signing")
}

val kotlinModuleName = "g000sha256.material.color_scheme"
val androidNamespace = kotlinModuleName

android {
    compileSdk = 34
    namespace = kotlinModuleName

    defaultConfig { minSdk = 21 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    explicitApi()
    withSourcesJar(publish = true)

    androidTarget {
        publishLibraryVariants("release")

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
            moduleName = kotlinModuleName
        }
    }

    jvm {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
            moduleName = kotlinModuleName
        }
    }

    js(IR) { browser() }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs { browser() }

    iosArm64()
    iosX64()
    iosSimulatorArm64()

    macosArm64()
    macosX64()

    sourceSets {
        commonMain {
            dependencies {
                implementation(catalog.library.jetbrains.annotations)
                implementation(catalog.library.jetbrains.kotlin)

                implementation(catalog.library.compose.material3)
                implementation(catalog.library.compose.ui.graphics)
                implementation(catalog.library.materialColorUtilities)
            }
        }

        commonTest {
            dependencies { implementation(catalog.test.kotlin) }
        }
    }
}

val dokkaJavaDocTaskProvider = tasks.dokkaHtml

val dokkaJavaDocJarTaskProvider = tasks.register<Jar>("dokkaJavaDocJar") {
    archiveClassifier = "javadoc"
    group = "documentation"
    dependsOn(dokkaJavaDocTaskProvider)

    val dokkaJavaDocTask = dokkaJavaDocTaskProvider.get()
    from(dokkaJavaDocTask.outputDirectory)
}

publishing {
    publications {
        withType<MavenPublication> {
            groupId = libraryGroup
            version = libraryVersion

            afterEvaluate { artifactId = artifactId.replace(projectName, libraryModule) }

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
            }

            artifact(dokkaJavaDocJarTaskProvider)
        }
    }
}

signing {
    val key = getProperty("Signing.Key") ?: getEnvironment("SIGNING_KEY")
    val password = getProperty("Signing.Password") ?: getEnvironment("SIGNING_PASSWORD")
    useInMemoryPgpKeys(key, password)

    sign(publishing.publications)
}

tasks.withType<Sign> {
    signatureType = object : AbstractSignatureType(), SignatureType by signatureType {

        override fun fileFor(toSign: File): File {
            val original = super.fileFor(toSign)
            return layout
                .buildDirectory
                .dir("signatures/$name")
                .get()
                .file(original.name)
                .asFile
        }

        override fun sign(signatory: Signatory, toSign: File): File {
            return super.sign(signatory, toSign)
        }

    }
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