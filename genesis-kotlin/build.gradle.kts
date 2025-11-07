plugins {
    java
    kotlin("jvm") version libs.versions.kotlin
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.bundles.asm)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}