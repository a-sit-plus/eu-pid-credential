package at.asitplus.wallet.eupid

import at.asitplus.signum.indispensable.cosef.io.coseCompliantSerializer
import at.asitplus.testballoon.invoke
import at.asitplus.wallet.lib.data.LocalDateOrInstant
import at.asitplus.wallet.lib.data.vckJsonSerializer
import de.infix.testBalloon.framework.testSuite
import io.kotest.matchers.shouldBe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlin.random.Random
import kotlin.random.nextUInt

@OptIn(ExperimentalSerializationApi::class)
val SdJwtSerializationTest by testSuite {

    "serialize credential" {
        val useLocalDate = Random.nextBoolean()
        val credential = EuPidCredentialSdJwt(
            familyName = randomString(),
            givenName = randomString(),
            birthDate = randomLocalDate(),
            ageEqualOrOver = AgeEqualOrOverSdJwt(
                equalOrOver12 = Random.nextBoolean(),
                equalOrOver14 = Random.nextBoolean(),
                equalOrOver16 = Random.nextBoolean(),
                equalOrOver18 = Random.nextBoolean(),
                equalOrOver21 = Random.nextBoolean(),
            ),
            ageInYears = Random.nextUInt(),
            ageBirthYear = Random.nextUInt(),
            familyNameBirth = randomString(),
            givenNameBirth = randomString(),
            placeOfBirth = PlaceOfBirthSdJwt(
                locality = randomString(),
            ),
            address = AddressSdJwt(
                formatted = randomString(),
                country = randomString(),
                region = randomString(),
                locality = randomString(),
                postalCode = randomString(),
                street = randomString(),
                houseNumber = randomString(),
            ),
            gender = IsoIec5218Gender.NOT_APPLICABLE.toString(),
            nationalities = setOf(randomString()),
            issuanceDate = localDateOrInstant(useLocalDate),
            expiryDate = localDateOrInstant(useLocalDate),
            issuingAuthority = randomString(),
            documentNumber = randomString(),
            issuingCountry = randomString(),
            issuingJurisdiction = randomString(),
            personalAdministrativeNumber = randomString(),
            portrait = Random.nextBytes(32),
            email = randomString(),
            phoneNumber = randomString(),
            trustAnchor = randomString(),
            locationStatus = randomString(),
        )
        val json = vckJsonSerializer.encodeToString(credential)
        vckJsonSerializer.decodeFromString<EuPidCredentialSdJwt>(json) shouldBe credential

        val cbor = coseCompliantSerializer.encodeToByteArray(credential)
        coseCompliantSerializer.decodeFromByteArray<EuPidCredentialSdJwt>(cbor) shouldBe credential
    }

}

private fun localDateOrInstant(useLocalDate: Boolean): LocalDateOrInstant =
    if (useLocalDate) LocalDateOrInstant.LocalDate(randomLocalDate()) else LocalDateOrInstant.Instant(randomInstant())
