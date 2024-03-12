package at.asitplus.wallet.eupid

import at.asitplus.wallet.eupid.EuPidScheme.Attributes
import at.asitplus.wallet.lib.data.CredentialSubject
import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * PID according to [EU PID Rule Book, v1.0.0 from November 2023](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-06-pid-rulebook.md)
 */
@Serializable
@SerialName("EuPid2023")
data class EuPidCredential(

    override val id: String,

    /**
     * Current last name(s) or surname(s) of the PID User.
     */
    @SerialName(Attributes.FAMILY_NAME)
    val familyName: String,

    /**
     * Current first name(s), including middle name(s), of the PID User.
     */
    @SerialName(Attributes.GIVEN_NAME)
    val givenName: String,

    /**
     * Day, month, and year on which the PID User was born.
     */
    @SerialName(Attributes.BIRTH_DATE)
    @Serializable(with = LocalDateIso8601Serializer::class)
    val birthDate: LocalDate,

    /**
     * Attesting whether the PID User is currently an adult (true) or a minor (false).
     */
    @SerialName(Attributes.AGE_OVER_18)
    val ageOver18: Boolean? = null,

    /**
     * The current age of the PID User in years.
     */
    @SerialName(Attributes.AGE_IN_YEARS)
    val ageInYears: UInt? = null,

    /**
     * The year when the PID User was born.
     */
    @SerialName(Attributes.AGE_BIRTH_YEAR)
    val ageBirthYear: UInt? = null,

    /**
     * Last name(s) or surname(s) of the PID User at the time of birth.
     */
    @SerialName(Attributes.FAMILY_NAME_BIRTH)
    val familyNameBirth: String? = null,

    /**
     * First name(s), including middle name(s), of the PID User at the time of birth.
     */
    @SerialName(Attributes.GIVEN_NAME_BIRTH)
    val givenNameBirth: String? = null,

    /**
     * The country, state, and city where the PID User was born.
     */
    @SerialName(Attributes.BIRTH_PLACE)
    val birthPlace: String? = null,

    /**
     * The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1.
     */
    @SerialName(Attributes.BIRTH_COUNTRY)
    val birthCountry: String? = null,

    /**
     * The state, province, district, or local area where the PID User was born.
     */
    @SerialName(Attributes.BIRTH_STATE)
    val birthState: String? = null,

    /**
     * The municipality, city, town, or village where the PID User was born.
     */
    @SerialName(Attributes.BIRTH_CITY)
    val birthCity: String? = null,

    /**
     * The full address of the place where the PID User currently resides and/or can be contacted (street name, house number, city etc.).
     */
    @SerialName(Attributes.RESIDENT_ADDRESS)
    val residentAddress: String? = null,

    /**
     * The country where the PID User currently resides, as an Alpha-2 country code as specified in ISO 3166-1.
     */
    @SerialName(Attributes.RESIDENT_COUNTRY)
    val residentCountry: String? = null,

    /**
     * The state, province, district, or local area where the PID User currently resides.
     */
    @SerialName(Attributes.RESIDENT_STATE)
    val residentState: String? = null,

    /**
     * The municipality, city, town, or village where the PID User currently resides.
     */
    @SerialName(Attributes.RESIDENT_CITY)
    val residentCity: String? = null,

    /**
     * Postal code of the place where the PID User currently resides.
     */
    @SerialName(Attributes.RESIDENT_POSTAL_CODE)
    val residentPostalCode: String? = null,

    /**
     * The name of the street where the PID User currently resides.
     */
    @SerialName(Attributes.RESIDENT_STREET)
    val residentStreet: String? = null,

    /**
     * The house number where the PID User currently resides, including any affix or suffix.
     */
    @SerialName(Attributes.RESIDENT_HOUSE_NUMBER)
    val residentHouseNumber: String? = null,

    /**
     * PID User’s gender, using a value as defined in ISO/IEC 5218.
     */
    @SerialName(Attributes.GENDER)
    val gender: IsoIec5218Gender? = null,

    /**
     * Alpha-2 country code as specified in ISO 3166-1, representing the nationality of the PID User.
     */
    @SerialName(Attributes.NATIONALITY)
    val nationality: String? = null,
) : CredentialSubject()


