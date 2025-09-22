import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

group = "dev.g000sha256"
version = "1.4.0"

plugins {
    alias(notation = catalog.plugins.android)
    alias(notation = catalog.plugins.g000sha256.sonatypeMavenCentral)
    alias(notation = catalog.plugins.gradle.mavenPublish)
    alias(notation = catalog.plugins.gradle.signing)
    alias(notation = catalog.plugins.jetBrains.binaryCompatibilityValidator)
    alias(notation = catalog.plugins.jetBrains.compose)
    alias(notation = catalog.plugins.jetBrains.dokka)
    alias(notation = catalog.plugins.jetBrains.kotlin)
}

val kotlinModuleName = "g000sha256.material.color_scheme"

android {
    compileSdk = 36
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

        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
            moduleName = kotlinModuleName
        }
    }

    jvm {
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
                implementation(dependencyNotation = catalog.libraries.jetBrains.annotations)
                implementation(dependencyNotation = catalog.libraries.jetBrains.kotlin)

                implementation(dependencyNotation = catalog.libraries.compose.animation)
                implementation(dependencyNotation = catalog.libraries.compose.animationCore)
                implementation(dependencyNotation = catalog.libraries.compose.material3)
                implementation(dependencyNotation = catalog.libraries.compose.runtime)
                implementation(dependencyNotation = catalog.libraries.compose.uiGraphics)
                implementation(dependencyNotation = catalog.libraries.materialColorUtilities)
            }
        }

        commonTest {
            dependencies {
                implementation(dependencyNotation = catalog.test.kotlin)
            }
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
            artifact(dokkaJavaDocJarTaskProvider)

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
                    developerConnection = "scm:git:git@github.com:g000sha256/material_color_scheme.git"
                    url = "https://github.com/g000sha256/material_color_scheme"
                }

                issueManagement {
                    system = "GitHub Issues"
                    url = "https://github.com/g000sha256/material_color_scheme/issues"
                }
            }
        }
    }
}

signing {
    val key = getProperty(key = "Signing.Key") ?: getEnvironment(key = "SIGNING_KEY")
    val password = getProperty(key = "Signing.Password") ?: getEnvironment(key = "SIGNING_PASSWORD")
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
    credentials {
        username = getProperty(key = "SonatypeMavenCentral.Username") ?: getEnvironment(key = "SONATYPE_USERNAME")
        password = getProperty(key = "SonatypeMavenCentral.Password") ?: getEnvironment(key = "SONATYPE_PASSWORD")
    }
}

private fun getProperty(key: String): String? {
    return properties.get(key = key) as String?
}

private fun getEnvironment(key: String): String? {
    return System.getenv(key)
}
