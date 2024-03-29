package at.asitplus.wallet.eupid


import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = IsoIec5218GenderSerializer::class)
enum class IsoIec5218Gender(val code: Int) {
    NOT_KNOWN(0),
    MALE(1),
    FEMALE(2),
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