plugins {
    id("at.asitplus.gradle.conventions") version "2.0.0+20240609"
}

val artifactVersion: String by extra
group = "at.asitplus.wallet"
version = artifactVersion

allprojects {
    repositories {
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
            name = "bigNum"
        }
    }
}