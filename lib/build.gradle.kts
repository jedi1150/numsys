import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinJvm

plugins {
    alias(libs.plugins.binaryCompatibilityValidator)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.vanniktech.maven.publish)
}

kotlin {
    explicitApi()
}

dependencies {
    testImplementation(kotlin("test"))
}

mavenPublishing {
    coordinates("io.github.jedi1150", "numsys", "0.0.4-rc")

    configure(
        KotlinJvm(
            javadocJar = JavadocJar.Dokka("dokkaHtml"),
            sourcesJar = true,
        )
    )

    pom {
        name.set("My Library")
        description.set("A description of what my library does.")
        inceptionYear.set("2024")
        url.set("https://github.com/jedi1150/numsys/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("jedi1150")
                name.set("Oleg Yakovlev")
                url.set("https://github.com/jedi1150/")
            }
        }
        scm {
            url.set("https://github.com/jedi1150/numsys/")
            connection.set("scm:git:git://github.com/jedi1150/numsys.git")
            developerConnection.set("scm:git:ssh://git@github.com/jedi1150/numsys.git")
        }
    }
}

publishing {
    repositories {
        maven {
            name = "githubPackages"
            url = uri("https://maven.pkg.github.com/jedi1150/numsys")
            credentials(PasswordCredentials::class)
        }
    }
}
