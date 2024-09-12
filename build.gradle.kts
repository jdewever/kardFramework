plugins {
    kotlin("jvm") version "2.0.10"
}

group = "xyz.jonasdewever"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}