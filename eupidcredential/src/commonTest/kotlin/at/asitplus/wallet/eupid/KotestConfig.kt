package io.kotest.provided

import kotlinx.datetime.LocalDate
import kotlin.random.Random
import kotlin.time.Instant

import at.asitplus.test.XmlReportingProjectConfig
import at.asitplus.wallet.eupid.Initializer

/** Wires KMP JUnit XML reporting */
class ProjectConfig : XmlReportingProjectConfig() {
    init {
        Initializer.initWithVCK()
    }
}
internal fun randomLocalDate() = LocalDate(Random.nextInt(1900, 2100), Random.nextInt(1, 12), Random.nextInt(1, 28))

internal fun randomInstant() = Instant.fromEpochSeconds(Random.nextLong(1000L, 3000L))

private val charPool = ('A'..'Z') + ('a'..'z') + ('0'..'9')

internal fun randomString() = (1..16)
    .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
    .joinToString("")
