plugins {
    val kotlinVer = System.getenv("KOTLIN_VERSION_ENV")?.ifBlank { null } ?: libs.versions.kotlin.get()

    id("at.asitplus.gradle.conventions") version "20251023"
    id("de.infix.testBalloon") version libs.versions.testballoon.get() apply false
    kotlin("multiplatform") version kotlinVer apply false
    kotlin("plugin.serialization") version kotlinVer apply false
}

val artifactVersion: String by extra
group = "at.asitplus.wallet"
version = artifactVersion
