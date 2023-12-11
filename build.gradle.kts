allprojects {
  tasks.register("hello") {
    doLast {
      println("I'm ${this.project.name}")
    }
  }
}

subprojects {
    apply(plugin = "java")
    // group = "org.gradle.sample"
    // version = "1.0"
    repositories {
      mavenCentral()
    }
}
