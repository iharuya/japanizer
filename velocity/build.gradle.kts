plugins {
  id("java")
  id("application")
  id("xyz.jpenilla.run-velocity") version "2.2.2"
  id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  implementation(project(":common"))
  compileOnly("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
  annotationProcessor("com.velocitypowered:velocity-api:3.2.0-SNAPSHOT")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

application {
  mainClass.set("github.iharuya.japanizer.velocity.Japanizer")
}

tasks {
  runVelocity {
    velocityVersion("3.2.0-SNAPSHOT")
  }

  jar {
    archiveFileName.set("japanizer-original.jar")
  }

  shadowJar {
    archiveBaseName.set("japanizer")
    archiveClassifier.set("all")
  }
}