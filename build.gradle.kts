plugins {
    java
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("io.vavr:vavr:0.10.4")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType(JavaCompile::class.java) {
    options.compilerArgs.add("--enable-preview")
}



application {
    mainClass.set("Main")
    applicationDefaultJvmArgs = listOf("--enable-preview")
}