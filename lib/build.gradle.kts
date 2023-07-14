plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}
publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            groupId = "com.github.jedi1150"
            artifactId = "numsys"
            version = "0.0.3"

            afterEvaluate {
                from(components["java"])
            }
        }
    }
}