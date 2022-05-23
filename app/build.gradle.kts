plugins {
    id("Aurora.Prime.kotlin-application-conventions")
}

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
    mainClass.set("Aurora.Prime.app.AppKt")
}
