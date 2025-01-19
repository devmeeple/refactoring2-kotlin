plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

group = "io.github.devmeeple"
version = "1.0-SNAPSHOT"

object Versions {
    const val KOTEST = "5.9.1"
    const val KOTLINX_SERIALIZATION = "1.8.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Kotest Test Framework + Assertions
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${Versions.KOTEST}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${Versions.KOTEST}")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
