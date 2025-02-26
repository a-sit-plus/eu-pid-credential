package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.JsonValueEncoder
import at.asitplus.wallet.lib.LibraryInitializer
import at.asitplus.wallet.lib.data.CredentialSubject
import at.asitplus.wallet.lib.data.vckJsonSerializer
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

object Initializer {

    /**
     * A reference to this class is enough to trigger the init block
     */
    init {
        initWithVCK()
    }

    /**
     * This has to be called first, before anything first, to load the
     * relevant classes of this library into the base implementations of VC-K
     */
    fun initWithVCK() {
        LibraryInitializer.registerExtensionLibrary(
            credentialScheme = EuPidScheme,
            serializersModule = SerializersModule {
                polymorphic(CredentialSubject::class) {
                    subclass(EuPidCredential::class)
                }
            },
            jsonValueEncoder = jsonValueEncoder(),
            itemValueSerializerMap = mapOf(
                EuPidScheme.Attributes.AGE_OVER_12 to Boolean.serializer(),
                EuPidScheme.Attributes.AGE_OVER_14 to Boolean.serializer(),
                EuPidScheme.Attributes.AGE_OVER_16 to Boolean.serializer(),
                EuPidScheme.Attributes.AGE_OVER_18 to Boolean.serializer(),
                EuPidScheme.Attributes.AGE_OVER_21 to Boolean.serializer(),
                EuPidScheme.Attributes.BIRTH_DATE to LocalDate.serializer(),
                EuPidScheme.Attributes.AGE_IN_YEARS to UInt.serializer(),
                EuPidScheme.Attributes.AGE_BIRTH_YEAR to UInt.serializer(),
                EuPidScheme.Attributes.GENDER to IsoIec5218GenderSerializer,
                EuPidScheme.Attributes.SEX to UInt.serializer(),
                EuPidScheme.Attributes.NATIONALITY to SetSerializer(String.serializer()),
                EuPidScheme.Attributes.ISSUANCE_DATE to Instant.serializer(),
                EuPidScheme.Attributes.EXPIRY_DATE to Instant.serializer(),
                EuPidScheme.Attributes.PORTRAIT to ByteArraySerializer(),
            )
        )
    }

    private fun jsonValueEncoder(): JsonValueEncoder = {
        when (it) {
            is IsoIec5218Gender -> vckJsonSerializer.encodeToJsonElement(it)
            is LocalDate -> vckJsonSerializer.encodeToJsonElement(it)
            is UInt -> vckJsonSerializer.encodeToJsonElement(it)
            is Instant -> vckJsonSerializer.encodeToJsonElement(it)
            else -> null
        }
    }

}
