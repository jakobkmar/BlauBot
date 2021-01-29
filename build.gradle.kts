import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * PROJECT
 */

group = "net.axay"
version = "0.0.1"

/**
 * PLUGINS
 */

plugins {
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"

    application
}

/**
 * DEPENDENCY MANAGEMENT
 */

repositories {
    mavenCentral()
    jcenter()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(kotlin("reflect"))

    implementation("dev.kord:kord-core:0.7.0-SNAPSHOT")
    implementation("com.gitlab.kordlib:kordx.emoji:0.4.0")

    implementation("io.github.config4k:config4k:0.4.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.google.code.gson:gson:2.8.6")

    implementation("org.slf4j:slf4j-simple:1.7.30")

    implementation("org.apache.commons:commons-lang3:3.11")

}

/**
 * BUILD
 */

application {
    mainClass.set("net.axay.blaubot.ManagerKt")
}

/**
 * JAVA VERSION
 */

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11
