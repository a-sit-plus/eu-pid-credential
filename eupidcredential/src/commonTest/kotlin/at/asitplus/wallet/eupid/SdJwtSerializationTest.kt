package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.vckJsonSerializer
import at.asitplus.wallet.lib.iso.vckCborSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.Clock
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.encodeToString
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalSerializationApi::class)
class SdJwtSerializationTest : FunSpec({

    test("serialize credential") {
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
            issuanceDate = Clock.System.now(),
            expiryDate = Clock.System.now().plus(300.seconds),
            issuingAuthority = randomString(),
            documentNumber = randomString(),
            issuingCountry = randomString(),
            issuingJurisdiction = randomString(),
            personalAdministrativeNumber = randomString(),
            portrait = Random.nextBytes(32),
        )
        val json = vckJsonSerializer.encodeToString(credential)
        vckJsonSerializer.decodeFromString<EuPidCredentialSdJwt>(json) shouldBe credential

        val cbor = vckCborSerializer.encodeToByteArray(credential)
        vckCborSerializer.decodeFromByteArray<EuPidCredentialSdJwt>(cbor) shouldBe credential
    }

})
