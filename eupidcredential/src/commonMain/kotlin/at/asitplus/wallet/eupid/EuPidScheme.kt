package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.ConstantIndex
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation.*


/**
 * PID scheme according to
 * [PID Rulebook](https://github.com/eu-digital-identity-wallet/eudi-doc-attestation-rulebooks-catalog/blob/main/rulebooks/pid/pid-rulebook.md)
 * from 2025-10-02.
 */
object EuPidScheme : ConstantIndex.CredentialScheme {
    override val schemaUri: String = "https://wallet.a-sit.at/schemas/1.0.0/eupid.json"
    override val vcType: String = "EuPid2023"
    override val isoNamespace: String = "eu.europa.ec.eudi.pid.1"
    override val isoDocType: String = "eu.europa.ec.eudi.pid.1"
    override val supportedRepresentations: Collection<ConstantIndex.CredentialRepresentation> =
        listOf(PLAIN_JWT, ISO_MDOC)

    override val claimNames: Collection<String> = listOf(
        Attributes.FAMILY_NAME,
        Attributes.GIVEN_NAME,
        Attributes.BIRTH_DATE,

        Attributes.FAMILY_NAME_BIRTH,
        Attributes.GIVEN_NAME_BIRTH,

        Attributes.RESIDENT_ADDRESS,
        Attributes.RESIDENT_COUNTRY,
        Attributes.RESIDENT_STATE,
        Attributes.RESIDENT_CITY,
        Attributes.RESIDENT_POSTAL_CODE,
        Attributes.RESIDENT_STREET,
        Attributes.RESIDENT_HOUSE_NUMBER,

        Attributes.SEX,
        Attributes.NATIONALITY,

        Attributes.ISSUANCE_DATE,
        Attributes.EXPIRY_DATE,
        Attributes.ISSUING_AUTHORITY,
        Attributes.DOCUMENT_NUMBER,
        Attributes.ISSUING_COUNTRY,
        Attributes.ISSUING_JURISDICTION,

        Attributes.PERSONAL_ADMINISTRATIVE_NUMBER,
        Attributes.PORTRAIT,
        Attributes.EMAIL_ADDRESS,
        Attributes.MOBILE_PHONE_NUMBER,
        Attributes.TRUST_ANCHOR,
        Attributes.LOCATION_STATUS,
    )

    val requiredClaimNames: Collection<String> = listOf(
        Attributes.FAMILY_NAME,
        Attributes.GIVEN_NAME,
        Attributes.BIRTH_DATE,
        Attributes.NATIONALITY,
        Attributes.EXPIRY_DATE,
        Attributes.ISSUING_AUTHORITY,
        Attributes.ISSUING_COUNTRY,
    )

    object Attributes {
        /** Current last name(s) or surname(s) of the user to whom the person identification data relates. */
        const val FAMILY_NAME = "family_name"

        /** Current first name(s), including middle name(s) where applicable, of the user to whom the person identification data relates. */
        const val GIVEN_NAME = "given_name"

        /** Day, month, and year on which the user to whom the person identification data relates was born. */
        const val BIRTH_DATE = "birth_date"

        /** Last name(s) or surname(s) of the User to whom the person identification data relates at the time of birth. */
        const val FAMILY_NAME_BIRTH = "family_name_birth"

        /** First name(s), including middle name(s), of the User to whom the person identification data relates at the time of birth. */
        const val GIVEN_NAME_BIRTH = "given_name_birth"

        /** Place of birth, see [PlaceOfBirth] */
        const val PLACE_OF_BIRTH = "place_of_birth"

        /**
         * The full address of the place where the user to whom the person identification data relates currently
         * resides or can be contacted (street name, house number, city etc.).
         */
        const val RESIDENT_ADDRESS = "resident_address"

        /** The country where the user to whom the person identification data relates currently resides, as an alpha-2
         *  country code as specified in ISO 3166-1. */
        const val RESIDENT_COUNTRY = "resident_country"

        /** The state, province, district, or local area where the user to whom the person identification data relates
         * currently resides. */
        const val RESIDENT_STATE = "resident_state"

        /** The municipality, city, town, or village where the PID User currently resides. */
        const val RESIDENT_CITY = "resident_city"

        /** The postal code of the place where the user to whom the person identification data relates currently resides. */
        const val RESIDENT_POSTAL_CODE = "resident_postal_code"

        /** The name of the street where the user to whom the person identification data relates currently resides. */
        const val RESIDENT_STREET = "resident_street"

        /** The house number where the user to whom the person identification data relates currently resides, including
         *  any affix or suffix. */
        const val RESIDENT_HOUSE_NUMBER = "resident_house_number"

        /** Values shall be one of the following: 0 = not known; 1 = male; 2 = female; 3 = other; 4 = inter;
         * 5 = diverse; 6 = open; 9 = not applicable. For values 0, 1, 2 and 9, ISO/IEC 5218 applies. */
        const val SEX = "sex"

        /** One or more alpha-2 country codes as specified in ISO 3166-1, representing the nationality of the user to
         *  whom the person identification data relates. */
        const val NATIONALITY = "nationality"

        /** Date (and if possible time) when the person identification data was issued and/or the administrative validity period of the person identification data began. */
        const val ISSUANCE_DATE = "issuance_date"

        /** Date (and if possible time) when the person identification data will expire. */
        const val EXPIRY_DATE = "expiry_date"

        /**
         * Name of the administrative authority that has issued this PID instance, or
         * the ISO 3166 Alpha-2 country code of the respective Member State if
         * there is no separate authority authorized to issue PIDs.
         */
        const val ISSUING_AUTHORITY = "issuing_authority"

        /** A number for the PID, assigned by the PID Provider. */
        const val DOCUMENT_NUMBER = "document_number"

        /** Alpha-2 country code, as defined in ISO 3166-1, of the PID Provider's country or territory. */
        const val ISSUING_COUNTRY = "issuing_country"

        /**
         * Country subdivision code of the jurisdiction that issued the PID, as
         * defined in ISO 3166-2:2020, Clause 8. The first part of the code SHALL
         * be the same as the value for [ISSUING_COUNTRY].
         */
        const val ISSUING_JURISDICTION = "issuing_jurisdiction"

        /**
         * A value assigned to the natural person that is unique among all personal administrative numbers issued by the
         * provider of person identification data. Where Member States opt to include this attribute, they shall
         * describe in their electronic identification schemes under which the person identification data is issued,
         * the policy that they apply to the values of this attribute, including, where applicable, specific conditions
         * for the processing of this value.
         */
        const val PERSONAL_ADMINISTRATIVE_NUMBER = "personal_administrative_number"

        /** Facial image of the wallet user compliant with ISO 19794-5 or ISO 39794 specifications. */
        const val PORTRAIT = "portrait"

        /** Electronic mail address of the user to whom the person identification data relates, in conformance with [RFC 5322]. */
        const val EMAIL_ADDRESS = "email_address"

        /** Mobile telephone number of the User to whom the person identification data relates, starting with the '+'
         * symbol as the international code prefix and the country code, followed by numbers only. */
        const val MOBILE_PHONE_NUMBER = "mobile_phone_number"

        /** This attribute indicates at least the URL at which a machine-readable version of the trust anchor to be used for verifying the PID can be found or looked up */
        const val TRUST_ANCHOR = "trust_anchor"

        /** The location of validity status information on the person identification data where the providers of person identification data revoke person identification data. */
        const val LOCATION_STATUS = "location_status"


        object PlaceOfBirth {
            /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
            const val COUNTRY = "country"

            /** The name of a state, province, district, or local area where the PID User was born. */
            const val REGION = "region"

            /** The name of a municipality, city, town, or village where the PID User was born. */
            const val LOCALITY = "locality"
        }

    }

}
