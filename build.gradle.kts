import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.graalvm.buildtools.native") version "0.9.18"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    id("java")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val springBootVersion = "2.7.4"

repositories {
    mavenCentral()
}

dependencies {
    //spring
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${springBootVersion}")

    //armeria
    implementation(platform("com.linecorp.armeria:armeria-bom:1.21.0"))
    implementation(platform("io.netty:netty-bom:4.1.86.Final"))
    implementation("com.linecorp.armeria:armeria-spring-boot2-webflux-starter")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")

    //kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

    //discord
    implementation("net.dv8tion:JDA:5.0.0-alpha.22")

    //util
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
