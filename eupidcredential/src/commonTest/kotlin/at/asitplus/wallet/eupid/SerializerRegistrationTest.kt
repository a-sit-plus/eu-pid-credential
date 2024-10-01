package at.asitplus.wallet.eupid

import at.asitplus.signum.indispensable.cosef.CoseHeader
import at.asitplus.signum.indispensable.cosef.CoseSigned
import at.asitplus.signum.indispensable.cosef.io.ByteStringWrapper
import at.asitplus.wallet.lib.iso.IssuerSignedItem
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.BIRTH_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_IN_YEARS
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.AGE_BIRTH_YEAR
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.GENDER
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.ISSUANCE_DATE
import at.asitplus.wallet.eupid.EuPidScheme.Attributes.EXPIRY_DATE
import at.asitplus.wallet.lib.agent.SubjectCredentialStore
import at.asitplus.wallet.lib.data.CredentialToJsonConverter
import at.asitplus.wallet.lib.iso.IssuerSigned
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.JsonObject
import kotlin.random.Random
import kotlin.random.nextUInt

class SerializerRegistrationTest : FreeSpec({

    "Serialization and deserialization" - {
        withData(
            nameFn = { "for ${it.key}" },
            dataMap().entries
        ) {
            val item = IssuerSignedItem(
                digestId = Random.nextUInt(),
                random = Random.nextBytes(32),
                elementIdentifier = it.key,
                elementValue = it.value
            )

            val serialized = item.serialize(EuPidScheme.isoNamespace)

            val deserialized = IssuerSignedItem.deserialize(serialized, EuPidScheme.isoNamespace).getOrThrow()

            deserialized.elementValue shouldBe it.value
        }
    }

    "Serialization to JSON Element" {
        val claims = dataMap()
        val namespacedItems: Map<String, List<IssuerSignedItem>> =
            mapOf(EuPidScheme.isoNamespace to claims.map {
                IssuerSignedItem(Random.nextUInt(), Random.nextBytes(32), it.key, it.value)
            }.toList())
        val issuerAuth = CoseSigned(ByteStringWrapper(CoseHeader()), null, null, byteArrayOf())
        val credential = SubjectCredentialStore.StoreEntry.Iso(
            IssuerSigned.fromIssuerSignedItems(namespacedItems, issuerAuth),
            EuPidScheme
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


private fun dataMap(): Map<String, Any> =
    mapOf(
        BIRTH_DATE to randomLocalDate(),
        AGE_IN_YEARS to Random.nextUInt(1u, 99u),
        AGE_BIRTH_YEAR to Random.nextUInt(1900u, 2100u),
        GENDER to IsoIec5218Gender.NOT_APPLICABLE,
        ISSUANCE_DATE to randomInstant(),
        EXPIRY_DATE to randomInstant(),
    )

private fun randomLocalDate() = LocalDate(Random.nextInt(1900, 2100), Random.nextInt(1, 12), Random.nextInt(1, 28))

private fun randomInstant() = Instant.fromEpochSeconds(Random.nextLong(1000L, 3000L))