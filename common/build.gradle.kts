plugins {
  id("github.iharuya.japanizer.java-library")
}

dependencies {
  // Use JUnit Jupiter for testing.
  implementation("com.google.guava:guava:32.1.1-jre")
  implementation("org.apache.commons:commons-lang3:3.14.0")
  implementation("com.google.code.gson:gson:2.7")
}

// java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

// application {
//   mainClass.set("github.iharuya.japanizer.common.Transliterator")
// }

// tasks.named<Test>("test") {
//   // Use JUnit Platform for unit tests.
//   useJUnitPlatform()
// }
