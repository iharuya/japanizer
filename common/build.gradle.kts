plugins {
  id("java")
  id("java-library")
}

dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")

  implementation("com.google.guava:guava:32.1.1-jre")
  implementation("org.apache.commons:commons-lang3:3.14.0")
  implementation("com.google.code.gson:gson:2.7")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

tasks.named<Test>("test") {
  useJUnitPlatform()
}
