package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.jsonSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate
import kotlinx.serialization.encodeToString
import kotlin.random.Random
import kotlin.random.nextUInt

class SerializerTest : FunSpec({

    test("serialize credential") {
        Initializer.initWithVcLib()

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
        )
        val serialized = jsonSerializer.encodeToString(credential)

        val parsed: EuPidCredential = jsonSerializer.decodeFromString(serialized)
        parsed shouldBe credential
    }

})


val charPool = ('A'..'Z') + ('a'..'z') + ('0'..'9')

fun randomString() = (1..16)
    .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
    .joinToString("")
