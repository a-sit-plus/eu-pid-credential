package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.LocalDateOrInstant
import at.asitplus.wallet.lib.data.vckJsonSerializer
import de.infix.testBalloon.framework.core.testSuite
import io.kotest.matchers.shouldBe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlin.random.Random

@OptIn(ExperimentalSerializationApi::class)
val JwtSerializationTest by testSuite {

    test("serialize credential") {
        val useLocalDate = Random.nextBoolean()
        val credential = EuPidCredential(
            id = randomString(),
            familyName = randomString(),
            givenName = randomString(),
            birthDate = randomLocalDate(),
            familyNameBirth = randomString(),
            givenNameBirth = randomString(),
            placeOfBirth = PlaceOfBirth(randomString(), randomString(), randomString()),
            residentAddress = randomString(),
            residentCountry = randomString(),
            residentState = randomString(),
            residentCity = randomString(),
            residentPostalCode = randomString(),
            residentStreet = randomString(),
            residentHouseNumber = randomString(),
            sex = IsoIec5218Gender.entries.random().code,
            nationalityElement = buildJsonArray { add(JsonPrimitive(randomString())) },
            issuanceDate = localDateOrInstant(useLocalDate),
            expiryDate = localDateOrInstant(useLocalDate),
            issuingAuthority = randomString(),
            documentNumber = randomString(),
            issuingCountry = randomString(),
            issuingJurisdiction = randomString(),
            personalAdministrativeNumber = randomString(),
            portrait = Random.nextBytes(32),
            emailAddress = randomString(),
            mobilePhoneNumber = randomString(),
            trustAnchor = randomString(),
            locationStatus = randomString(),
        )
        val json = vckJsonSerializer.encodeToString(credential)
        vckJsonSerializer.decodeFromString<EuPidCredential>(json) shouldBe credential
    }

}

private fun localDateOrInstant(useLocalDate: Boolean): LocalDateOrInstant =
    if (useLocalDate) LocalDateOrInstant.LocalDate(randomLocalDate()) else LocalDateOrInstant.Instant(randomInstant())
