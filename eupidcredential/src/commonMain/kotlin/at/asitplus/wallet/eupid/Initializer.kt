package at.asitplus.wallet.eupid

import at.asitplus.signum.indispensable.josef.io.joseCompliantSerializer
import at.asitplus.wallet.lib.JsonValueEncoder
import at.asitplus.wallet.lib.LibraryInitializer
import at.asitplus.wallet.lib.data.CredentialSubject
import at.asitplus.wallet.lib.data.LocalDateOrInstant
import at.asitplus.wallet.lib.data.LocalDateOrInstantSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlin.time.Instant

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
                EuPidScheme.Attributes.BIRTH_DATE to LocalDate.serializer(),
                EuPidScheme.Attributes.SEX to UInt.serializer(),
                EuPidScheme.Attributes.NATIONALITY to SetSerializer(String.serializer()),
                EuPidScheme.Attributes.ISSUANCE_DATE to LocalDateOrInstantSerializer,
                EuPidScheme.Attributes.EXPIRY_DATE to LocalDateOrInstantSerializer,
                EuPidScheme.Attributes.PORTRAIT to ByteArraySerializer(),
                EuPidScheme.Attributes.PLACE_OF_BIRTH to PlaceOfBirth.serializer(),
            )
        )
    }

    private fun jsonValueEncoder(): JsonValueEncoder = {
        when (it) {
            is IsoIec5218Gender -> joseCompliantSerializer.encodeToJsonElement(it)
            is LocalDate -> joseCompliantSerializer.encodeToJsonElement(it)
            is UInt -> joseCompliantSerializer.encodeToJsonElement(it)
            is Instant -> joseCompliantSerializer.encodeToJsonElement(it)
            is LocalDateOrInstant -> joseCompliantSerializer.encodeToJsonElement(it)
            is PlaceOfBirth -> joseCompliantSerializer.encodeToJsonElement(it)
            else -> null
        }
    }

}
