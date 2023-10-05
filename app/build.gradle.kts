plugins {
  // Apply the application plugin to add support for building a CLI application in Java.
  application

  id("xyz.jpenilla.run-paper") version "2.2.0"
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  // Use JUnit Jupiter for testing.
  testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  // This dependency is used by the application.
  implementation("com.google.guava:guava:32.1.1-jre")

  // implementation("net.kyori:adventure-api:4.14.0")

  compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
}

// Apply a specific Java toolchain to ease working on different environments.
java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

application {
  // Define the main class for the application.
  mainClass.set("japanizer.App")
}

tasks.named<Test>("test") {
  // Use JUnit Platform for unit tests.
  useJUnitPlatform()
}

tasks { runServer { minecraftVersion("1.20.2") } }
