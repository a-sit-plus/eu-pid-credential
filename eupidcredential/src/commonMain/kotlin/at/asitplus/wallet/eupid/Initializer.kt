package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.LibraryInitializer
import at.asitplus.wallet.lib.data.CredentialSubject
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

object Initializer {

    /**
     * A reference to this class is enough to trigger the init block
     */
    init {
        initWithVcLib()
    }

    /**
     * This has to be called first, before anything first, to load the
     * relevant classes of this library into the base implementations of vclib
     */
    fun initWithVcLib() {
        LibraryInitializer.registerExtensionLibrary(
            LibraryInitializer.ExtensionLibraryInfo(
                credentialScheme = EuPidScheme,
                serializersModule = SerializersModule {
                    polymorphic(CredentialSubject::class) {
                        subclass(EuPidCredential::class)
                    }
                },
            )
        )
    }

}
