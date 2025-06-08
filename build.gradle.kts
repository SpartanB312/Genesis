plugins {
    kotlin("jvm") version libs.versions.kotlin apply false
}

group = "net.spartanb312"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()
        maven("https://repo1.maven.org/maven2/")
        maven("https://mvnrepository.com/artifact/")
    }
}