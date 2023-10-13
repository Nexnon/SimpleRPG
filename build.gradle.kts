plugins {
    id("java")
}

group = "ru.vsu.cs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":generator")))
    implementation(project(mapOf("path" to ":serialization")))
    implementation(project(mapOf("path" to ":entities")))
    implementation(project(mapOf("path" to ":deserialization")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}