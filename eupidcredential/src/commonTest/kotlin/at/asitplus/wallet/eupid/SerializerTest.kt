package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.vckJsonSerializer
import at.asitplus.wallet.lib.iso.vckCborSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.encodeToString
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.time.Duration.Companion.seconds

class SerializerTest : FunSpec({

    test("serialize credential") {
        val credential = EuPidCredential(
            id = randomString(),
            familyName = randomString(),
            givenName = randomString(),
            birthDate = LocalDate.fromEpochDays(2),
            ageOver18 = true,
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
        val serialized = vckJsonSerializer.encodeToString(credential)

        val parsed: EuPidCredential = vckJsonSerializer.decodeFromString(serialized)
        parsed shouldBe credential

        val cbor  = vckCborSerializer.encodeToByteArray(credential)
        val decoded: EuPidCredential = vckCborSerializer.decodeFromByteArray(cbor)

        decoded shouldBe credential
    }

})


val charPool = ('A'..'Z') + ('a'..'z') + ('0'..'9')

fun randomString() = (1..16)
    .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
    .joinToString("")
