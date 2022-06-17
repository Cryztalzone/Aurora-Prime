plugins {
    id("Aurora.Prime.kotlin-application-conventions")
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "dev.cryztalzone"
version = "2.0-INDEV"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":utilities"))
    implementation("net.dv8tion:JDA:5.0.0-alpha.12")
}

application {
    // Define the main class for the application.
    mainClass.set("dev.cryztalzone.aurora_prime.app.AppKt")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "dev.cryztalzone.aurora_prime.app.AppKt"
}

project.setProperty("mainClassName", "dev.cryztalzone.aurora_prime.app.AppKt")