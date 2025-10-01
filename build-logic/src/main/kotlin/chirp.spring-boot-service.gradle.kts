plugins {
    id("chirp.kotlin-common")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
}

dependencies {
    "implementation"(libraries.findLibrary("kotlin.reflect").get())
    "implementation"(libraries.findLibrary("kotlin.stdlib").get())
    "implementation"(libraries.findLibrary("spring-boot-starter-webflux").get())

    "annotationProcessor"(libraries.findLibrary("spring-boot-processor").get())

    "testImplementation"(libraries.findLibrary("kotlin-test-junit5").get())
    "testImplementation"(libraries.findLibrary("spring-boot-starter-test").get())
    "testRuntimeOnly"(libraries.findLibrary("junit-platform-launcher").get())
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
