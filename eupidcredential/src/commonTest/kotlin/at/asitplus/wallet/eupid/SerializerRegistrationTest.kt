package at.asitplus.wallet.eupid

import at.asitplus.signum.indispensable.CryptoSignature
import at.asitplus.signum.indispensable.cosef.CoseEllipticCurve
import at.asitplus.signum.indispensable.cosef.CoseHeader
import at.asitplus.signum.indispensable.cosef.CoseKey
import at.asitplus.signum.indispensable.cosef.CoseKeyParams
import at.asitplus.signum.indispensable.cosef.CoseKeyType
import at.asitplus.signum.indispensable.cosef.CoseSigned
import at.asitplus.signum.indispensable.cosef.io.ByteStringWrapper
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ADMINISTRATIVE_NUMBER
import at.asitplus.wallet.lib.iso.IssuerSignedItem
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.BIRTH_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_IN_YEARS
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_BIRTH_YEAR
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_OVER_12
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_OVER_14
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_OVER_16
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_OVER_18
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_OVER_21
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.BIRTH_CITY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.BIRTH_COUNTRY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.BIRTH_PLACE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.BIRTH_STATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.DOCUMENT_NUMBER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.GENDER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUANCE_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.EXPIRY_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.FAMILY_NAME
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.FAMILY_NAME_BIRTH
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.GIVEN_NAME
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.GIVEN_NAME_BIRTH
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUING_AUTHORITY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUING_COUNTRY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUING_JURISDICTION
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.NATIONALITY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_ADDRESS
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_CITY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_COUNTRY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_HOUSE_NUMBER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_POSTAL_CODE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_STATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_STREET
import at.asitplus.wallet.lib.agent.SubjectCredentialStore
import at.asitplus.wallet.lib.data.CredentialToJsonConverter
import at.asitplus.wallet.lib.iso.DeviceKeyInfo
import at.asitplus.wallet.lib.iso.IssuerSigned
import at.asitplus.wallet.lib.iso.MobileSecurityObject
import at.asitplus.wallet.lib.iso.ValidityInfo
import at.asitplus.wallet.lib.iso.ValueDigest
import at.asitplus.wallet.lib.iso.ValueDigestList
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.JsonObject
import kotlin.random.Random
import kotlin.random.nextUInt

class SerializerRegistrationTest : FreeSpec({

    "Serialization and deserialization" - {
        withData(nameFn = { "for ${it.key}" }, dataMap().entries) {
            val item = it.toIssuerSignedItem()

            val serialized = item.serialize(EuPidScheme.isoNamespace)

            val deserialized = IssuerSignedItem.deserialize(serialized, EuPidScheme.isoNamespace, item.elementIdentifier).getOrThrow()

            deserialized.elementValue shouldBe it.value
        }
    }

    "Serialization to JSON Element" {
        val mso = MobileSecurityObject(
            version = "1.0",
            digestAlgorithm = "SHA-256",
            valueDigests = mapOf("foo" to ValueDigestList(listOf(ValueDigest(0U, byteArrayOf())))),
            deviceKeyInfo = deviceKeyInfo(),
            docType = "docType",
            validityInfo = ValidityInfo(Clock.System.now(), Clock.System.now(), Clock.System.now())
        )
        val claims = dataMap()
        val namespacedItems: Map<String, List<IssuerSignedItem>> =
            mapOf(EuPidScheme.isoNamespace to claims.map { it.toIssuerSignedItem() }.toList())
        val issuerAuth = CoseSigned.create(CoseHeader(), null, mso, CryptoSignature.RSAorHMAC(byteArrayOf(1,3,3,7)),
            MobileSecurityObject.serializer()
        )
        val credential = SubjectCredentialStore.StoreEntry.Iso(
            IssuerSigned.fromIssuerSignedItems(namespacedItems, issuerAuth),
            EuPidScheme.isoNamespace
        )
        val converted = CredentialToJsonConverter.toJsonElement(credential)
            .shouldBeInstanceOf<JsonObject>()
            .also { println(it) }
        val jsonMap = converted[EuPidScheme.isoNamespace]
            .shouldBeInstanceOf<JsonObject>()

        claims.forEach {
            withClue("Serialization for ${it.key}") {
                jsonMap[it.key].shouldNotBeNull()
            }
        }
    }
})

private fun Map.Entry<String, Any>.toIssuerSignedItem() =
    IssuerSignedItem(Random.nextUInt(), Random.nextBytes(32), key, value)


private fun dataMap(): Map<String, Any> =
    mapOf(
        FAMILY_NAME to randomString(),
        GIVEN_NAME to randomString(),
        BIRTH_DATE to randomLocalDate(),
        AGE_OVER_12 to Random.nextBoolean(),
        AGE_OVER_14 to Random.nextBoolean(),
        AGE_OVER_16 to Random.nextBoolean(),
        AGE_OVER_18 to Random.nextBoolean(),
        AGE_OVER_21 to Random.nextBoolean(),
        AGE_IN_YEARS to Random.nextUInt(1u, 99u),
        AGE_BIRTH_YEAR to Random.nextUInt(1900u, 2100u),
        FAMILY_NAME_BIRTH to randomString(),
        GIVEN_NAME_BIRTH to randomString(),
        BIRTH_PLACE to randomString(),
        BIRTH_COUNTRY to randomString(),
        BIRTH_STATE to randomString(),
        BIRTH_CITY to randomString(),
        RESIDENT_ADDRESS to randomString(),
        RESIDENT_COUNTRY to randomString(),
        RESIDENT_STATE to randomString(),
        RESIDENT_CITY to randomString(),
        RESIDENT_POSTAL_CODE to randomString(),
        RESIDENT_STREET to randomString(),
        RESIDENT_HOUSE_NUMBER to randomString(),
        GENDER to IsoIec5218Gender.NOT_APPLICABLE,
        NATIONALITY to randomString(),
        ISSUANCE_DATE to randomInstant(),
        EXPIRY_DATE to randomInstant(),
        ISSUING_AUTHORITY to randomString(),
        DOCUMENT_NUMBER to randomString(),
        ADMINISTRATIVE_NUMBER to randomString(),
        ISSUING_COUNTRY to randomString(),
        ISSUING_JURISDICTION to randomString(),
    )

private fun randomLocalDate() = LocalDate(Random.nextInt(1900, 2100), Random.nextInt(1, 12), Random.nextInt(1, 28))

private fun randomInstant() = Instant.fromEpochSeconds(Random.nextLong(1000L, 3000L))

private fun deviceKeyInfo() =
    DeviceKeyInfo(CoseKey(CoseKeyType.EC2, keyParams = CoseKeyParams.EcYBoolParams(CoseEllipticCurve.P256)))