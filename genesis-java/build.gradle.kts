plugins {
    java
}

repositories {
    mavenCentral()
    maven("https://repo1.maven.org/maven2/")
    maven("https://mvnrepository.com/artifact/")
}

val asmVersion = "9.7"

dependencies {
    implementation("org.ow2.asm:asm:$asmVersion")
    implementation("org.ow2.asm:asm-tree:$asmVersion")
    implementation("org.ow2.asm:asm-commons:$asmVersion")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
}