import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    java
    kotlin("jvm") version libs.versions.kotlin
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.bundles.asm)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    compileKotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }
    }
}