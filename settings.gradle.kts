pluginManagement {
  repositories.gradlePluginPortal()
  includeBuild("build-logic")
}

dependencyResolutionManagement {
  repositories.mavenCentral()
  includeBuild(".")
}

rootProject.name = "japanizer"
include("common", "paper")
