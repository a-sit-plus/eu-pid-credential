package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.LocalDateOrInstant
import at.asitplus.wallet.lib.data.vckJsonSerializer
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.provided.randomInstant
import io.kotest.provided.randomLocalDate
import io.kotest.provided.randomString
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlin.random.Random
import kotlin.random.nextUInt

@OptIn(ExperimentalSerializationApi::class)
class JwtSerializationTest : FunSpec({

    test("serialize credential") {
        val useLocalDate = Random.nextBoolean()
        val credential = EuPidCredential(
            id = randomString(),
            familyName = randomString(),
            givenName = randomString(),
            birthDate = randomLocalDate(),
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

})

private fun localDateOrInstant(useLocalDate: Boolean): LocalDateOrInstant =
    if (useLocalDate) LocalDateOrInstant.LocalDate(randomLocalDate()) else LocalDateOrInstant.Instant(randomInstant())
