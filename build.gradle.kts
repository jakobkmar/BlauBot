import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "net.axay"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.serialization") version "1.4.32"

    application
}

repositories {
    mavenCentral()
    maven {
        name = "Kotlin Discord"
        url = uri("https://maven.kotlindiscord.com/repository/maven-public/")
    }
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    implementation("com.kotlindiscord.kord.extensions:kord-extensions:1.4.0-RC7")
    implementation("com.gitlab.kordlib:kordx.emoji:0.4.0")

    implementation("io.github.config4k:config4k:0.4.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")

    implementation("org.slf4j:slf4j-simple:1.7.30")

    implementation("org.apache.commons:commons-lang3:3.12.0")
}

application {
    mainClass.set("net.axay.blaubot.ManagerKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11
