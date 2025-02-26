package at.asitplus.wallet.eupid


import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Extended with values from [EU PID Rule Book, v1.5.0 from February 2025](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-3/annex-3.01-pid-rulebook.md)
 */
@Serializable(with = IsoIec5218GenderSerializer::class)
enum class IsoIec5218Gender(val code: Int) {
    NOT_KNOWN(0),
    MALE(1),
    FEMALE(2),
    OTHER(3),
    INTER(4),
    DIVERSE(5),
    OPEN(6),
    NOT_APPLICABLE(9)
}

object IsoIec5218GenderSerializer : KSerializer<IsoIec5218Gender> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("IsoIec5218Gender", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): IsoIec5218Gender {
        val decoded = decoder.decodeInt()
        return IsoIec5218Gender.entries.first { it.code == decoded }
    }

    override fun serialize(encoder: Encoder, value: IsoIec5218Gender) {
        encoder.encodeInt(value.code)
    }

}