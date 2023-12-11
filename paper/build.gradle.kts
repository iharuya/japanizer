plugins {
  id("github.iharuya.japanizer.java-application")
  id("xyz.jpenilla.run-paper") version "2.2.2"
}

repositories {
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  compileOnly(project(":common"))
  compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
}


application {
  mainClass.set("github.iharuya.japanizer.paper.Japanizer")
}

tasks {
  runServer {
    minecraftVersion("1.20.2")
  }

  //  build/libs/japanizer.jarを生成
  jar {
    archiveFileName.set("japanizer.jar")
  }
}
