plugins {
    java
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

ext {
    mapstructVersion = "1.5.5.Final"
    lombokVersion = "1.18.30"
    lombokMapstructBinding = "0.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // MapStruct
    implementation("org.mapstruct:mapstruct:$mapstructVersion")

    // Lombok + binding para MapStruct
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    implementation("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBinding")

    // Annotation processors
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBinding")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.example.Main")
}
