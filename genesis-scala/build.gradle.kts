plugins {
    java
    kotlin("jvm") version libs.versions.kotlin
    scala
}

dependencies {
    implementation(libs.scala3.library)
    implementation(libs.bundles.asm)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

scala {
    zincVersion = "1.10.8"
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