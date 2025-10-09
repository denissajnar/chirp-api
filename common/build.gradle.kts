plugins {
    id("java-library")
    id("chirp.kotlin-common")
}

group = "dev.denissajnar"
version = "unspecified"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}


dependencies {
    api(libs.uuid.creator)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
