plugins {
  id("java")
  id("application")
  id("xyz.jpenilla.run-paper") version "2.2.2"
  id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  implementation(project(":common"))
  compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

application {
  mainClass.set("github.iharuya.japanizer.paper.Japanizer")
}

tasks {
  runServer {
    minecraftVersion("1.20.2")
  }

  jar {
    archiveFileName.set("japanizer-original.jar")
  }

  shadowJar {
    archiveBaseName.set("japanizer")
    archiveClassifier.set("all")
  }
}