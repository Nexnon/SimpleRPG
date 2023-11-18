plugins {
    id("java")
}

group = "ru.vsu.cs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.6.0")
    implementation(project(mapOf("path" to ":entities")))
    implementation(project(mapOf("path" to ":generator")))
}

tasks.test {
    useJUnitPlatform()
}