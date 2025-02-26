package at.asitplus.wallet.eupid

import at.asitplus.wallet.eupid.EuPidScheme.SdJwtAttributes
import io.matthewnelson.encoding.base64.Base64
import io.matthewnelson.encoding.core.Encoder.Companion.encodeToString
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * PID according to [EU PID Rule Book, v1.5.0 from February 2025](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-3/annex-3.01-pid-rulebook.md)
 * with mapping of claim names according to [PR #160](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/pull/160)
 **/
@Serializable
data class EuPidCredentialSdJwt(
    /** Current last name(s) or surname(s) of the user to whom the person identification data relates. */
    @SerialName(SdJwtAttributes.FAMILY_NAME)
    val familyName: String,

    /** Current first name(s), including middle name(s), of the PID User. */
    @SerialName(SdJwtAttributes.GIVEN_NAME)
    val givenName: String,

    /** Day, month, and year on which the user to whom the person identification data relates was born. */
    @SerialName(SdJwtAttributes.BIRTH_DATE)
    @Serializable(with = LocalDateIso8601Serializer::class)
    val birthDate: LocalDate,

    /** Additional current age attestations. */
    @SerialName(SdJwtAttributes.PREFIX_AGE_EQUAL_OR_OVER)
    val ageEqualOrOver: AgeEqualOrOverSdJwt,

    /** The current age of the User to whom the person identification data relates in years. */
    @SerialName(SdJwtAttributes.AGE_IN_YEARS)
    val ageInYears: UInt? = null,

    /** The year when the User to whom the person identification data relates was born. */
    @SerialName(SdJwtAttributes.AGE_BIRTH_YEAR)
    val ageBirthYear: UInt? = null,

    /** Last name(s) or surname(s) of the User to whom the person identification data relates at the time of birth. */
    @SerialName(SdJwtAttributes.FAMILY_NAME_BIRTH)
    val familyNameBirth: String? = null,

    /** First name(s), including middle name(s), of the User to whom the person identification data relates at the time of birth. */
    @SerialName(SdJwtAttributes.GIVEN_NAME_BIRTH)
    val givenNameBirth: String? = null,

    /** Place of birth. */
    @SerialName(SdJwtAttributes.PREFIX_PLACE_OF_BIRTH)
    val placeOfBirth: PlaceOfBirthSdJwt,

    /** Address. */
    @SerialName(SdJwtAttributes.PREFIX_ADDRESS)
    val address: AddressSdJwt,

    /** PID User’s gender, using a string value like `female`, `male`, or custom text values. */
    @SerialName(SdJwtAttributes.GENDER)
    val gender: String? = null,

    /** Array of Alpha-2 country code as specified in ISO 3166-1, representing the nationality of the PID User. */
    @SerialName(SdJwtAttributes.NATIONALITIES)
    val nationalities: Set<String>? = null,

    /** Date (and if possible time) when the person identification data was issued and/or the administrative validity period of the person identification data began. */
    @SerialName(SdJwtAttributes.ISSUANCE_DATE)
    val issuanceDate: Instant,

    /** Date (and if possible time) when the person identification data will expire. */
    @SerialName(SdJwtAttributes.EXPIRY_DATE)
    val expiryDate: Instant,

    /**
     * Name of the administrative authority that has issued this PID instance, or
     * the ISO 3166 Alpha-2 country code of the respective Member State if
     * there is no separate authority authorized to issue PIDs.
     */
    @SerialName(SdJwtAttributes.ISSUING_AUTHORITY)
    val issuingAuthority: String,

    /** A number for the PID, assigned by the PID Provider. */
    @SerialName(SdJwtAttributes.DOCUMENT_NUMBER)
    val documentNumber: String? = null,

    /** A number assigned by the PID Provider for audit control or other purposes. */
    @Suppress("DEPRECATION")
    @Deprecated("Removed in ARF 1.5.0")
    @SerialName(SdJwtAttributes.ADMINISTRATIVE_NUMBER)
    val administrativeNumber: String? = null,

    /** Alpha-2 country code, as defined in ISO 3166-1, of the PID Provider's country or territory. */
    @SerialName(SdJwtAttributes.ISSUING_COUNTRY)
    val issuingCountry: String,

    /**
     * Country subdivision code of the jurisdiction that issued the PID, as
     * defined in ISO 3166-2:2020, Clause 8. The first part of the code SHALL
     * be the same as the value for [issuingCountry].
     */
    @SerialName(SdJwtAttributes.ISSUING_JURISDICTION)
    val issuingJurisdiction: String? = null,

    /**
     * A value assigned to the natural person that is unique among all personal administrative numbers issued by the
     * provider of person identification data. Where Member States opt to include this attribute, they shall
     * describe in their electronic identification schemes under which the person identification data is issued,
     * the policy that they apply to the values of this attribute, including, where applicable, specific conditions
     * for the processing of this value.
     */
    @SerialName(SdJwtAttributes.PERSONAL_ADMINISTRATIVE_NUMBER)
    val personalAdministrativeNumber: String? = null,

    /** Facial image of the wallet user compliant with ISO 19794-5 or ISO 39794 specifications. */
    @SerialName(SdJwtAttributes.PORTRAIT)
    val portrait: ByteArray? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EuPidCredentialSdJwt

        if (familyName != other.familyName) return false
        if (givenName != other.givenName) return false
        if (birthDate != other.birthDate) return false
        if (ageEqualOrOver != other.ageEqualOrOver) return false
        if (ageInYears != other.ageInYears) return false
        if (ageBirthYear != other.ageBirthYear) return false
        if (familyNameBirth != other.familyNameBirth) return false
        if (givenNameBirth != other.givenNameBirth) return false
        if (placeOfBirth != other.placeOfBirth) return false
        if (address != other.address) return false
        if (gender != other.gender) return false
        if (nationalities != other.nationalities) return false
        if (issuanceDate != other.issuanceDate) return false
        if (expiryDate != other.expiryDate) return false
        if (issuingAuthority != other.issuingAuthority) return false
        if (documentNumber != other.documentNumber) return false
        if (administrativeNumber != other.administrativeNumber) return false
        if (issuingCountry != other.issuingCountry) return false
        if (issuingJurisdiction != other.issuingJurisdiction) return false
        if (personalAdministrativeNumber != other.personalAdministrativeNumber) return false
        if (!portrait.contentEquals(other.portrait)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = familyName.hashCode()
        result = 31 * result + givenName.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + ageEqualOrOver.hashCode()
        result = 31 * result + (ageInYears?.hashCode() ?: 0)
        result = 31 * result + (ageBirthYear?.hashCode() ?: 0)
        result = 31 * result + (familyNameBirth?.hashCode() ?: 0)
        result = 31 * result + (givenNameBirth?.hashCode() ?: 0)
        result = 31 * result + placeOfBirth.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + (gender?.hashCode() ?: 0)
        result = 31 * result + (nationalities?.hashCode() ?: 0)
        result = 31 * result + issuanceDate.hashCode()
        result = 31 * result + expiryDate.hashCode()
        result = 31 * result + issuingAuthority.hashCode()
        result = 31 * result + (documentNumber?.hashCode() ?: 0)
        result = 31 * result + (administrativeNumber?.hashCode() ?: 0)
        result = 31 * result + issuingCountry.hashCode()
        result = 31 * result + (issuingJurisdiction?.hashCode() ?: 0)
        result = 31 * result + (personalAdministrativeNumber?.hashCode() ?: 0)
        result = 31 * result + (portrait?.contentHashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "EuPidCredentialSdJwt(" +
                "familyName='$familyName', " +
                "givenName='$givenName', " +
                "birthDate=$birthDate, " +
                "ageEqualOrOver=$ageEqualOrOver, " +
                "ageInYears=$ageInYears, " +
                "ageBirthYear=$ageBirthYear, " +
                "familyNameBirth=$familyNameBirth, " +
                "givenNameBirth=$givenNameBirth, " +
                "placeOfBirth=$placeOfBirth, " +
                "address=$address, " +
                "gender=$gender, " +
                "nationalities=$nationalities, " +
                "issuanceDate=$issuanceDate, " +
                "expiryDate=$expiryDate, " +
                "issuingAuthority='$issuingAuthority', " +
                "documentNumber=$documentNumber, " +
                "administrativeNumber=$administrativeNumber, " +
                "issuingCountry='$issuingCountry', " +
                "issuingJurisdiction=$issuingJurisdiction, " +
                "personalAdministrativeNumber=$personalAdministrativeNumber, " +
                "portrait=${portrait?.encodeToString(Base64())}" +
                ")"
    }
}

@Serializable
data class AgeEqualOrOverSdJwt(
    /** Additional current age attestations: Attesting whether the PID User is currently over 12 years old. */
    @SerialName(SdJwtAttributes.AgeEqualOrOver.EQUAL_OR_OVER_12)
    val equalOrOver12: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 14 years old. */
    @SerialName(SdJwtAttributes.AgeEqualOrOver.EQUAL_OR_OVER_14)
    val equalOrOver14: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 16 years old. */
    @SerialName(SdJwtAttributes.AgeEqualOrOver.EQUAL_OR_OVER_16)
    val equalOrOver16: Boolean? = null,

    /** Attesting whether the User to whom the person identification data relates is currently an adult (true) or a minor (false). */
    @SerialName(SdJwtAttributes.AgeEqualOrOver.EQUAL_OR_OVER_18)
    val equalOrOver18: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 21 years old. */
    @SerialName(SdJwtAttributes.AgeEqualOrOver.EQUAL_OR_OVER_21)
    val equalOrOver21: Boolean? = null,
)

@Serializable
data class PlaceOfBirthSdJwt(
    /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
    @Suppress("DEPRECATION")
    @Deprecated("Removed in ARF 1.5.0")
    @SerialName(SdJwtAttributes.PlaceOfBirth.COUNTRY)
    val country: String? = null,

    /** The state, province, district, or local area where the PID User was born. */
    @Suppress("DEPRECATION")
    @Deprecated("Removed in ARF 1.5.0")
    @SerialName(SdJwtAttributes.PlaceOfBirth.REGION)
    val region: String? = null,

    /** The municipality, city, town, or village where the PID User was born. */
    @SerialName(SdJwtAttributes.PlaceOfBirth.LOCALITY)
    val locality: String? = null,
)

@Serializable
data class AddressSdJwt(
    /**
     * The full address of the place where the user to whom the person identification data relates currently resides
     * or can be contacted (street name, house number, city etc.).
     */
    @SerialName(SdJwtAttributes.Address.FORMATTED)
    val formatted: String? = null,

    /** The country where the user to whom the person identification data relates currently resides, as an alpha-2
     *  country code as specified in ISO 3166-1. */
    @SerialName(SdJwtAttributes.Address.COUNTRY)
    val country: String? = null,

    /** The state, province, district, or local area where the user to whom the person identification data relates
     * currently resides. */
    @SerialName(SdJwtAttributes.Address.REGION)
    val region: String? = null,

    /** The municipality, city, town, or village where the user to whom the person identification data relates currently
     *  resides. */
    @SerialName(SdJwtAttributes.Address.LOCALITY)
    val locality: String? = null,

    /** The postal code of the place where the user to whom the person identification data relates currently resides. */
    @SerialName(SdJwtAttributes.Address.POSTAL_CODE)
    val postalCode: String? = null,

    /** The name of the street where the user to whom the person identification data relates currently resides. */
    @SerialName(SdJwtAttributes.Address.STREET)
    val street: String? = null,

    /** The house number where the user to whom the person identification data relates currently resides, including any
     * affix or suffix. */
    @SerialName(SdJwtAttributes.Address.HOUSE_NUMBER)
    val houseNumber: String? = null,
)
