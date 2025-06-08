plugins {
    java
    kotlin("jvm") version libs.versions.kotlin
    scala
}

dependencies {
    implementation(libs.scala3.library)
    implementation(libs.bundles.asm)
}

scala {
    zincVersion = "1.10.8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    toolchain.languageVersion = JavaLanguageVersion.of(8)
}

tasks.compileScala {
    targetCompatibility = "8"

    scalaCompileOptions.apply {
        isForce = true

        forkOptions.apply {
            memoryMaximumSize = "8g"
            jvmArgs = listOf("-XX:MaxMetaspaceSize=512m")
        }

        additionalParameters = listOf("-Yexplicit-nulls", "-experimental")
    }
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}