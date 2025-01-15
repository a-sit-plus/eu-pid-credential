package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.vckJsonSerializer
import at.asitplus.wallet.lib.iso.vckCborSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.encodeToString
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalSerializationApi::class)
class JwtSerializationTest : FunSpec({

    test("serialize credential") {
        val credential = EuPidCredential(
            id = randomString(),
            familyName = randomString(),
            givenName = randomString(),
            birthDate = LocalDate.fromEpochDays(2),
            ageOver12 = Random.nextBoolean(),
            ageOver14 = Random.nextBoolean(),
            ageOver16 = Random.nextBoolean(),
            ageOver18 = Random.nextBoolean(),
            ageOver21 = Random.nextBoolean(),
            ageInYears = Random.nextUInt(),
            ageBirthYear = Random.nextUInt(),
            familyNameBirth = randomString(),
            givenNameBirth = randomString(),
            birthPlace = randomString(),
            birthCountry = randomString(),
            birthState = randomString(),
            birthCity = randomString(),
            residentAddress = randomString(),
            residentCountry = randomString(),
            residentState = randomString(),
            residentCity = randomString(),
            residentPostalCode = randomString(),
            residentStreet = randomString(),
            residentHouseNumber = randomString(),
            gender = IsoIec5218Gender.NOT_APPLICABLE,
            nationality = randomString(),
            issuanceDate = Clock.System.now(),
            expiryDate = Clock.System.now().plus(300.seconds),
            issuingAuthority = randomString(),
            documentNumber = randomString(),
            administrativeNumber = randomString(),
            issuingCountry = randomString(),
            issuingJurisdiction = randomString(),
        )
        val json = vckJsonSerializer.encodeToString(credential)
        vckJsonSerializer.decodeFromString<EuPidCredential>(json) shouldBe credential

        val cbor = vckCborSerializer.encodeToByteArray(credential)
        vckCborSerializer.decodeFromByteArray<EuPidCredential>(cbor) shouldBe credential
    }

})
