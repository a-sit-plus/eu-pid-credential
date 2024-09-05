pluginManagement {
    repositories {
        maven {
            url = uri("https://raw.githubusercontent.com/a-sit-plus/gradle-conventions-plugin/mvn/repo")
            name = "aspConventions"
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots") //KOTEST snapshot
    }
}

includeBuild("..") {
    dependencySubstitution {
        substitute(module("at.asitplus.wallet:vck")).using(project(":vck"))
        substitute(module("at.asitplus.wallet:vck-openid")).using(project(":vck-openid"))
        substitute(module("at.asitplus.wallet:vck-aries")).using(project(":vck-aries"))
    }
}

rootProject.name = "eu-pid-credential"
include(":eupidcredential")
