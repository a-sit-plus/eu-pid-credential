package at.asitplus.wallet.eupid

import at.asitplus.iso.DeviceKeyInfo
import at.asitplus.iso.IssuerSigned
import at.asitplus.iso.IssuerSignedItem
import at.asitplus.iso.IssuerSignedItemSerializer
import at.asitplus.iso.MobileSecurityObject
import at.asitplus.iso.ValidityInfo
import at.asitplus.iso.ValueDigest
import at.asitplus.iso.ValueDigestList
import at.asitplus.signum.indispensable.CryptoSignature
import at.asitplus.signum.indispensable.cosef.CoseEllipticCurve
import at.asitplus.signum.indispensable.cosef.CoseHeader
import at.asitplus.signum.indispensable.cosef.CoseKey
import at.asitplus.signum.indispensable.cosef.CoseKeyParams
import at.asitplus.signum.indispensable.cosef.CoseKeyType
import at.asitplus.signum.indispensable.cosef.CoseSigned
import at.asitplus.signum.indispensable.cosef.io.coseCompliantSerializer
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.BIRTH_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.DOCUMENT_NUMBER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.EMAIL_ADDRESS
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.EXPIRY_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.FAMILY_NAME
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.FAMILY_NAME_BIRTH
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.GIVEN_NAME
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.GIVEN_NAME_BIRTH
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUANCE_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUING_AUTHORITY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUING_COUNTRY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUING_JURISDICTION
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.LOCATION_STATUS
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.MOBILE_PHONE_NUMBER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.NATIONALITY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.PERSONAL_ADMINISTRATIVE_NUMBER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.PLACE_OF_BIRTH
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.PORTRAIT
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_ADDRESS
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_CITY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_COUNTRY
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_HOUSE_NUMBER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_POSTAL_CODE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_STATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.RESIDENT_STREET
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.SEX
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.TRUST_ANCHOR
import at.asitplus.wallet.lib.agent.SubjectCredentialStore
import at.asitplus.wallet.lib.data.CredentialToJsonConverter
import at.asitplus.wallet.lib.data.LocalDateOrInstant
import de.infix.testBalloon.framework.core.testSuite
import io.kotest.assertions.withClue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.serialization.json.JsonObject
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.time.Clock


val IsoSerializationTest by testSuite {

    test("Serialization and deserialization for instant") {
        dataMap().entries.forEach {
            withClue("key=${it.key}") {
                val item = it.toIssuerSignedItem()

                val serialized = item.serialize(EuPidScheme.isoNamespace)

                IssuerSignedItem.deserialize(serialized, EuPidScheme.isoNamespace, item.elementIdentifier)
                    .apply {
                        if (elementIdentifier == ISSUANCE_DATE || elementIdentifier == EXPIRY_DATE) {
                            elementValue.shouldBeInstanceOf<LocalDateOrInstant.Instant>()
                                .value shouldBe it.value
                        } else {
                            elementValue shouldBe it.value
                        }
                    }
            }
        }
    }

    test("Serialization and deserialization for local date") {
        dataMap(useLocalDate = true).forEach {
            withClue("key=${it.key}") {
                val item = it.toIssuerSignedItem()

                val serialized = item.serialize(EuPidScheme.isoNamespace)

                IssuerSignedItem.deserialize(serialized, EuPidScheme.isoNamespace, item.elementIdentifier)
                    .apply {
                        if (elementIdentifier == ISSUANCE_DATE || elementIdentifier == EXPIRY_DATE) {
                            elementValue.shouldBeInstanceOf<LocalDateOrInstant.LocalDate>()
                                .value shouldBe it.value
                        } else {
                            elementValue shouldBe it.value
                        }
                    }
            }
        }
    }

    test("Serialization to JSON Element") {
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
        val issuerAuth = CoseSigned.create(
            CoseHeader(), null, mso, CryptoSignature.RSA(byteArrayOf(1, 3, 3, 7)),
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
}

private fun Map.Entry<String, Any>.toIssuerSignedItem() =
    IssuerSignedItem(Random.nextUInt(), Random.nextBytes(32), key, value)

@Suppress("DEPRECATION")
private fun dataMap(useLocalDate: Boolean = false): Map<String, Any> = mapOf(
    FAMILY_NAME to randomString(),
    GIVEN_NAME to randomString(),
    BIRTH_DATE to randomLocalDate(),
    FAMILY_NAME_BIRTH to randomString(),
    GIVEN_NAME_BIRTH to randomString(),
    PLACE_OF_BIRTH to PlaceOfBirth(randomString(), randomString(), randomString()),
    RESIDENT_ADDRESS to randomString(),
    RESIDENT_COUNTRY to randomString(),
    RESIDENT_STATE to randomString(),
    RESIDENT_CITY to randomString(),
    RESIDENT_POSTAL_CODE to randomString(),
    RESIDENT_STREET to randomString(),
    RESIDENT_HOUSE_NUMBER to randomString(),
    SEX to IsoIec5218Gender.entries.random().code,
    NATIONALITY to setOf(randomString()),
    ISSUANCE_DATE to if (useLocalDate) randomLocalDate() else randomInstant(),
    EXPIRY_DATE to if (useLocalDate) randomLocalDate() else randomInstant(),
    ISSUING_AUTHORITY to randomString(),
    DOCUMENT_NUMBER to randomString(),
    ISSUING_COUNTRY to randomString(),
    ISSUING_JURISDICTION to randomString(),
    PERSONAL_ADMINISTRATIVE_NUMBER to randomString(),
    PORTRAIT to Random.nextBytes(32),
    EMAIL_ADDRESS to randomString(),
    MOBILE_PHONE_NUMBER to "+${randomString()}",
    TRUST_ANCHOR to randomString(),
    LOCATION_STATUS to randomString(),
)

private fun deviceKeyInfo() =
    DeviceKeyInfo(CoseKey(CoseKeyType.EC2, keyParams = CoseKeyParams.EcYBoolParams(CoseEllipticCurve.P256)))

private fun IssuerSignedItem.serialize(namespace: String): ByteArray =
    coseCompliantSerializer.encodeToByteArray(IssuerSignedItemSerializer(namespace, elementIdentifier), this)

private fun IssuerSignedItem.Companion.deserialize(
    serialized: ByteArray,
    namespace: String,
    elementIdentifier: String,
): IssuerSignedItem =
    coseCompliantSerializer.decodeFromByteArray(IssuerSignedItemSerializer(namespace, elementIdentifier), serialized)
