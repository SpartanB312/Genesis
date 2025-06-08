plugins {
    java
}

dependencies {
    implementation(libs.bundles.asm)
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
}