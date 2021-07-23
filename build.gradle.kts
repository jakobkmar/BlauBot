import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "net.axay"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"

    application
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1")

    implementation("dev.kord:kord-core:0.7.4")
    implementation("dev.kord.x:emoji:0.5.0")

    implementation("io.github.config4k:config4k:0.4.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    implementation("org.slf4j:slf4j-simple:1.7.30")

    implementation("org.apache.commons:commons-lang3:3.12.0")
}

application {
    mainClass.set("net.axay.blaubot.ManagerKt")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    withType<JavaCompile> {
        options.release.set(11)
        options.encoding = "UTF-8"
    }
}
