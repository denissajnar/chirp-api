plugins {
    id("java-library")
    id("chirp.spring-boot-service")
}

group = "dev.denissajnar"
version = "unspecified"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}


dependencies {
    implementation(projects.common)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.springdoc.openapi.starter.webflux.ui)
    implementation(libs.spring.boot.starter.flyway)
    implementation(libs.flyway.database.postgresql)
    implementation(libs.spring.boot.starter.data.r2dbc)
    implementation(libs.spring.jdbc)
    implementation(libs.jwt.api)

    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.r2dbc.postgresql)
    runtimeOnly(libs.jwt.impl)
    runtimeOnly(libs.jwt.jackson)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
