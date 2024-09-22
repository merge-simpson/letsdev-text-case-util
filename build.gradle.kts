plugins {
    id("java")
    id("maven-publish")
}

group = "me.letsdev"
version = "0.1.0"

java {
    toolchain {
//        languageVersion = JavaLanguageVersion.of(21)
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

repositories {
    mavenCentral()
}

dependencies {

}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.github.merge-simpson"
            artifactId = project.name
            version = project.version.toString()
        }
    }
}

tasks.named("publishToMavenLocal").configure {
    dependsOn("assemble")
}

tasks.named<Jar>("jar") {
    enabled = true
    archiveClassifier.set("") // remove suffix if set via another plugin
}