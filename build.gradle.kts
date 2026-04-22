plugins {
    alias(libs.plugins.asitplus.gradle.conventions)
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.testballoon) apply false
}

val artifactVersion: String by extra
group = "at.asitplus.wallet"
version = artifactVersion
