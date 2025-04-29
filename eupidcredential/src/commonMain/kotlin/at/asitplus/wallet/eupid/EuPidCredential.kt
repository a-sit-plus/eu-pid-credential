package at.asitplus.wallet.eupid

import at.asitplus.wallet.eupid.EuPidScheme.Attributes
import at.asitplus.wallet.lib.data.CredentialSubject
import io.matthewnelson.encoding.base64.Base64
import io.matthewnelson.encoding.core.Encoder.Companion.encodeToString
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive


/**
 * PID according to [EU PID Rule Book, v1.5.0 from February 2025](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-3/annex-3.01-pid-rulebook.md)
 */
@Serializable
@SerialName("EuPid2023")
data class EuPidCredential(

    override val id: String,

    /** Current last name(s) or surname(s) of the user to whom the person identification data relates. */
    @SerialName(Attributes.FAMILY_NAME)
    val familyName: String,

    /** Current first name(s), including middle name(s) where applicable, of the user to whom the person identification data relates. */
    @SerialName(Attributes.GIVEN_NAME)
    val givenName: String,

    /** Day, month, and year on which the user to whom the person identification data relates was born. */
    @SerialName(Attributes.BIRTH_DATE)
    @Serializable(with = LocalDateIso8601Serializer::class)
    val birthDate: LocalDate,

    /** Additional current age attestations: Attesting whether the PID User is currently over 12 years old. */
    @SerialName(Attributes.AGE_OVER_12)
    val ageOver12: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 13 years old. */
    @SerialName(Attributes.AGE_OVER_13)
    val ageOver13: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 14 years old. */
    @SerialName(Attributes.AGE_OVER_14)
    val ageOver14: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 16 years old. */
    @SerialName(Attributes.AGE_OVER_16)
    val ageOver16: Boolean? = null,

    /** Attesting whether the User to whom the person identification data relates is currently an adult (true) or a minor (false). */
    @SerialName(Attributes.AGE_OVER_18)
    val ageOver18: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 21 years old. */
    @SerialName(Attributes.AGE_OVER_21)
    val ageOver21: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 25 years old. */
    @SerialName(Attributes.AGE_OVER_25)
    val ageOver25: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 60 years old. */
    @SerialName(Attributes.AGE_OVER_60)
    val ageOver60: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 62 years old. */
    @SerialName(Attributes.AGE_OVER_62)
    val ageOver62: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 65 years old. */
    @SerialName(Attributes.AGE_OVER_65)
    val ageOver65: Boolean? = null,

    /** Additional current age attestations: Attesting whether the PID User is currently over 68 years old. */
    @SerialName(Attributes.AGE_OVER_68)
    val ageOver68: Boolean? = null,

    /** The current age of the User to whom the person identification data relates in years. */
    @SerialName(Attributes.AGE_IN_YEARS)
    val ageInYears: UInt? = null,

    /** The year when the User to whom the person identification data relates was born. */
    @SerialName(Attributes.AGE_BIRTH_YEAR)
    val ageBirthYear: UInt? = null,

    /** Last name(s) or surname(s) of the User to whom the person identification data relates at the time of birth. */
    @SerialName(Attributes.FAMILY_NAME_BIRTH)
    val familyNameBirth: String? = null,

    /** First name(s), including middle name(s), of the User to whom the person identification data relates at the time of birth. */
    @SerialName(Attributes.GIVEN_NAME_BIRTH)
    val givenNameBirth: String? = null,

    /** The country as an alpha-2 country code as specified in ISO 3166-1, or the state, province, district, or local
     * area or the municipality, city, town, or village where the user to whom the person identification data relates
     * was born. */
    @SerialName(Attributes.BIRTH_PLACE)
    val birthPlace: String? = null,

    /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
    @Suppress("DEPRECATION")
    @Deprecated("Removed in ARF 1.5.0")
    @SerialName(Attributes.BIRTH_COUNTRY)
    val birthCountry: String? = null,

    /** The state, province, district, or local area where the PID User was born. */
    @Suppress("DEPRECATION")
    @Deprecated("Removed in ARF 1.5.0")
    @SerialName(Attributes.BIRTH_STATE)
    val birthState: String? = null,

    /** The municipality, city, town, or village where the PID User was born. */
    @Suppress("DEPRECATION")
    @Deprecated("Removed in ARF 1.5.0")
    @SerialName(Attributes.BIRTH_CITY)
    val birthCity: String? = null,

    /**
     * The full address of the place where the user to whom the person identification data relates currently resides or
     * can be contacted (street name, house number, city etc.).
     */
    @SerialName(Attributes.RESIDENT_ADDRESS)
    val residentAddress: String? = null,

    /** The country where the user to whom the person identification data relates currently resides, as an alpha-2
     * country code as specified in ISO 3166-1. */
    @SerialName(Attributes.RESIDENT_COUNTRY)
    val residentCountry: String? = null,

    /** The state, province, district, or local area where the user to whom the person identification data relates
     * currently resides. */
    @SerialName(Attributes.RESIDENT_STATE)
    val residentState: String? = null,

    /** The municipality, city, town, or village where the user to whom the person identification data relates currently
     *  resides. */
    @SerialName(Attributes.RESIDENT_CITY)
    val residentCity: String? = null,

    /** The postal code of the place where the user to whom the person identification data relates currently resides. */
    @SerialName(Attributes.RESIDENT_POSTAL_CODE)
    val residentPostalCode: String? = null,

    /** The name of the street where the user to whom the person identification data relates currently resides. */
    @SerialName(Attributes.RESIDENT_STREET)
    val residentStreet: String? = null,

    /** The house number where the user to whom the person identification data relates currently resides, including any
     *  affix or suffix. */
    @SerialName(Attributes.RESIDENT_HOUSE_NUMBER)
    val residentHouseNumber: String? = null,

    /** PID User’s gender, using a value as defined in ISO/IEC 5218. */
    @SerialName(Attributes.GENDER)
    @Deprecated("Replaced with sex in ARF 1.5.0", ReplaceWith("sex"))
    val gender: IsoIec5218Gender? = null,

    /** Values shall be one of the following: 0 = not known; 1 = male; 2 = female; 3 = other; 4 = inter; 5 = diverse;
     * 6 = open; 9 = not applicable. For values 0, 1, 2 and 9, ISO/IEC 5218 applies. */
    @SerialName(Attributes.SEX)
    val sex: UInt? = null,

    /** One or more alpha-2 country codes as specified in ISO 3166-1, representing the nationality of the user to whom
     *  the person identification data relates.
     *  Before ARF 1.5.0, this has been a single string, now it may contain more than one entry. */
    @SerialName(Attributes.NATIONALITY)
    val nationalityElement: JsonElement? = null,

    /** Date (and if possible time) when the person identification data was issued and/or the administrative validity period of the person identification data began. */
    @SerialName(Attributes.ISSUANCE_DATE)
    val issuanceDate: Instant,

    /** Date (and if possible time) when the person identification data will expire. */
    @SerialName(Attributes.EXPIRY_DATE)
    val expiryDate: Instant,

    /**
     * Name of the administrative authority that has issued this PID instance, or
     * the ISO 3166 Alpha-2 country code of the respective Member State if
     * there is no separate authority authorized to issue PIDs.
     */
    @SerialName(Attributes.ISSUING_AUTHORITY)
    val issuingAuthority: String,

    /** A number for the PID, assigned by the PID Provider. */
    @SerialName(Attributes.DOCUMENT_NUMBER)
    val documentNumber: String? = null,

    /** A number assigned by the PID Provider for audit control or other purposes. */
    @Suppress("DEPRECATION")
    @Deprecated("Removed in ARF 1.5.0")
    @SerialName(Attributes.ADMINISTRATIVE_NUMBER)
    val administrativeNumber: String? = null,

    /** Alpha-2 country code, as defined in ISO 3166-1, of the PID Provider's country or territory. */
    @SerialName(Attributes.ISSUING_COUNTRY)
    val issuingCountry: String,

    /**
     * Country subdivision code of the jurisdiction that issued the PID, as
     * defined in ISO 3166-2:2020, Clause 8. The first part of the code SHALL
     * be the same as the value for [issuingCountry].
     */
    @SerialName(Attributes.ISSUING_JURISDICTION)
    val issuingJurisdiction: String? = null,

    /**
     * A value assigned to the natural person that is unique among all personal administrative numbers issued by the
     * provider of person identification data. Where Member States opt to include this attribute, they shall
     * describe in their electronic identification schemes under which the person identification data is issued,
     * the policy that they apply to the values of this attribute, including, where applicable, specific conditions
     * for the processing of this value.
     */
    @SerialName(Attributes.PERSONAL_ADMINISTRATIVE_NUMBER)
    val personalAdministrativeNumber: String? = null,

    /** Facial image of the wallet user compliant with ISO 19794-5 or ISO 39794 specifications. */
    @SerialName(Attributes.PORTRAIT)
    val portrait: ByteArray? = null,

    /** Electronic mail address of the user to whom the person identification data relates, in conformance with [RFC 5322]. */
    @SerialName(Attributes.EMAIL_ADDRESS)
    val emailAddress: String? = null,

    /** Mobile telephone number of the User to whom the person identification data relates, starting with the '+'
     * symbol as the international code prefix and the country code, followed by numbers only. */
    @SerialName(Attributes.MOBILE_PHONE_NUMBER)
    val mobilePhoneNumber: String? = null,

    /** This attribute indicates at least the URL at which a machine-readable version of the trust anchor to be used for verifying the PID can be found or looked up */
    @SerialName(Attributes.TRUST_ANCHOR)
    val trustAnchor: String? = null,

    /** The location of validity status information on the person identification data where the providers of person identification data revoke person identification data. */
    @SerialName(Attributes.LOCATION_STATUS)
    val locationStatus: String? = null,
) : CredentialSubject() {

    /** Values shall be one of the following: 0 = not known; 1 = male; 2 = female; 3 = other; 4 = inter; 5 = diverse;
     * 6 = open; 9 = not applicable. For values 0, 1, 2 and 9, ISO/IEC 5218 applies. */
    val sexAsEnum: IsoIec5218Gender? by lazy {
        sex?.let { IsoIec5218Gender.entries.firstOrNull { it.code == sex } }
    }

    /** One or more alpha-2 country codes as specified in ISO 3166-1, representing the nationality of the user to whom
     *  the person identification data relates.*/
    val nationality: String? by lazy {
        nationalityElement?.let { runCatching { it.jsonPrimitive.content }.getOrNull() }
    }

    /** One or more alpha-2 country codes as specified in ISO 3166-1, representing the nationality of the user to whom
     *  the person identification data relates.*/
    val nationalities: Collection<String>? by lazy {
        nationalityElement?.let { runCatching { it.jsonArray.map { it.jsonPrimitive.toString() } }.getOrNull() }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EuPidCredential

        if (ageOver12 != other.ageOver12) return false
        if (ageOver14 != other.ageOver14) return false
        if (ageOver16 != other.ageOver16) return false
        if (ageOver18 != other.ageOver18) return false
        if (ageOver21 != other.ageOver21) return false
        if (id != other.id) return false
        if (familyName != other.familyName) return false
        if (givenName != other.givenName) return false
        if (birthDate != other.birthDate) return false
        if (ageInYears != other.ageInYears) return false
        if (ageBirthYear != other.ageBirthYear) return false
        if (familyNameBirth != other.familyNameBirth) return false
        if (givenNameBirth != other.givenNameBirth) return false
        if (birthPlace != other.birthPlace) return false
        if (birthCountry != other.birthCountry) return false
        if (birthState != other.birthState) return false
        if (birthCity != other.birthCity) return false
        if (residentAddress != other.residentAddress) return false
        if (residentCountry != other.residentCountry) return false
        if (residentState != other.residentState) return false
        if (residentCity != other.residentCity) return false
        if (residentPostalCode != other.residentPostalCode) return false
        if (residentStreet != other.residentStreet) return false
        if (residentHouseNumber != other.residentHouseNumber) return false
        if (gender != other.gender) return false
        if (sex != other.sex) return false
        if (nationality != other.nationality) return false
        if (issuanceDate != other.issuanceDate) return false
        if (expiryDate != other.expiryDate) return false
        if (issuingAuthority != other.issuingAuthority) return false
        if (documentNumber != other.documentNumber) return false
        if (administrativeNumber != other.administrativeNumber) return false
        if (issuingCountry != other.issuingCountry) return false
        if (issuingJurisdiction != other.issuingJurisdiction) return false
        if (personalAdministrativeNumber != other.personalAdministrativeNumber) return false
        if (!portrait.contentEquals(other.portrait)) return false
        if (emailAddress != other.emailAddress) return false
        if (mobilePhoneNumber != other.mobilePhoneNumber) return false
        if (trustAnchor != other.trustAnchor) return false
        if (locationStatus != other.locationStatus) return false

        return true
    }

    override fun hashCode(): Int {
        var result = ageOver12?.hashCode() ?: 0
        result = 31 * result + (ageOver14?.hashCode() ?: 0)
        result = 31 * result + (ageOver16?.hashCode() ?: 0)
        result = 31 * result + (ageOver18?.hashCode() ?: 0)
        result = 31 * result + (ageOver21?.hashCode() ?: 0)
        result = 31 * result + id.hashCode()
        result = 31 * result + familyName.hashCode()
        result = 31 * result + givenName.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + (ageInYears?.hashCode() ?: 0)
        result = 31 * result + (ageBirthYear?.hashCode() ?: 0)
        result = 31 * result + (familyNameBirth?.hashCode() ?: 0)
        result = 31 * result + (givenNameBirth?.hashCode() ?: 0)
        result = 31 * result + (birthPlace?.hashCode() ?: 0)
        result = 31 * result + (birthCountry?.hashCode() ?: 0)
        result = 31 * result + (birthState?.hashCode() ?: 0)
        result = 31 * result + (birthCity?.hashCode() ?: 0)
        result = 31 * result + (residentAddress?.hashCode() ?: 0)
        result = 31 * result + (residentCountry?.hashCode() ?: 0)
        result = 31 * result + (residentState?.hashCode() ?: 0)
        result = 31 * result + (residentCity?.hashCode() ?: 0)
        result = 31 * result + (residentPostalCode?.hashCode() ?: 0)
        result = 31 * result + (residentStreet?.hashCode() ?: 0)
        result = 31 * result + (residentHouseNumber?.hashCode() ?: 0)
        result = 31 * result + (gender?.hashCode() ?: 0)
        result = 31 * result + (sex?.hashCode() ?: 0)
        result = 31 * result + (nationality?.hashCode() ?: 0)
        result = 31 * result + issuanceDate.hashCode()
        result = 31 * result + expiryDate.hashCode()
        result = 31 * result + issuingAuthority.hashCode()
        result = 31 * result + (documentNumber?.hashCode() ?: 0)
        result = 31 * result + (administrativeNumber?.hashCode() ?: 0)
        result = 31 * result + issuingCountry.hashCode()
        result = 31 * result + (issuingJurisdiction?.hashCode() ?: 0)
        result = 31 * result + (personalAdministrativeNumber?.hashCode() ?: 0)
        result = 31 * result + (portrait?.contentHashCode() ?: 0)
        result = 31 * result + (emailAddress?.hashCode() ?: 0)
        result = 31 * result + (mobilePhoneNumber?.hashCode() ?: 0)
        result = 31 * result + (trustAnchor?.hashCode() ?: 0)
        result = 31 * result + (locationStatus?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "EuPidCredential(" +
                "id='$id', " +
                "familyName='$familyName', " +
                "givenName='$givenName', " +
                "birthDate=$birthDate, " +
                "ageOver12=$ageOver12, " +
                "ageOver14=$ageOver14, " +
                "ageOver16=$ageOver16, " +
                "ageOver18=$ageOver18, " +
                "ageOver21=$ageOver21, " +
                "ageInYears=$ageInYears, " +
                "ageBirthYear=$ageBirthYear, " +
                "familyNameBirth=$familyNameBirth, " +
                "givenNameBirth=$givenNameBirth, " +
                "birthPlace=$birthPlace, " +
                "birthCountry=$birthCountry, " +
                "birthState=$birthState, " +
                "birthCity=$birthCity, " +
                "residentAddress=$residentAddress, " +
                "residentCountry=$residentCountry, " +
                "residentState=$residentState, " +
                "residentCity=$residentCity, " +
                "residentPostalCode=$residentPostalCode, " +
                "residentStreet=$residentStreet, " +
                "residentHouseNumber=$residentHouseNumber, " +
                "gender=$gender, " +
                "sex=$sex, " +
                "nationality=$nationality, " +
                "issuanceDate=$issuanceDate, " +
                "expiryDate=$expiryDate, " +
                "issuingAuthority='$issuingAuthority', " +
                "documentNumber=$documentNumber, " +
                "administrativeNumber=$administrativeNumber, " +
                "issuingCountry='$issuingCountry', " +
                "issuingJurisdiction=$issuingJurisdiction, " +
                "personalAdministrativeNumber=$personalAdministrativeNumber, " +
                "portrait=${portrait?.encodeToString(Base64())}, " +
                "emailAddress=$emailAddress, " +
                "mobilePhoneNumber=$mobilePhoneNumber, " +
                "trustAnchor=$trustAnchor, " +
                "locationStatus=$locationStatus" +
                ")"
    }

}


