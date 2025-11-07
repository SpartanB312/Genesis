plugins {
    java
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

dependencies {
    implementation(libs.bundles.asm)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}