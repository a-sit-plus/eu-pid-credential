package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.ConstantIndex
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation.*


/**
 * PID scheme according to [EU PID Rule Book, v1.5.0 from February 2025](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-3/annex-3.01-pid-rulebook.md)
 */
object EuPidScheme : ConstantIndex.CredentialScheme {
    override val schemaUri: String = "https://wallet.a-sit.at/schemas/1.0.0/eupid.json"
    override val vcType: String = "EuPid2023"
    override val isoNamespace: String = "eu.europa.ec.eudi.pid.1"
    override val isoDocType: String = "eu.europa.ec.eudi.pid.1"
    override val sdJwtType: String = "urn:eu.europa.ec.eudi:pid:1"
    override val supportedRepresentations: Collection<ConstantIndex.CredentialRepresentation> =
        listOf(PLAIN_JWT, SD_JWT, ISO_MDOC)

    @Suppress("DEPRECATION")
    override val claimNames: Collection<String> = listOf(
        Attributes.FAMILY_NAME,
        Attributes.GIVEN_NAME,
        Attributes.BIRTH_DATE,

        Attributes.AGE_OVER_12,
        Attributes.AGE_OVER_14,
        Attributes.AGE_OVER_16,
        Attributes.AGE_OVER_18,
        Attributes.AGE_OVER_21,
        Attributes.AGE_IN_YEARS,
        Attributes.AGE_BIRTH_YEAR,
        Attributes.FAMILY_NAME_BIRTH,
        Attributes.GIVEN_NAME_BIRTH,
        Attributes.BIRTH_PLACE,
        Attributes.BIRTH_COUNTRY,
        Attributes.BIRTH_STATE,
        Attributes.BIRTH_CITY,

        Attributes.RESIDENT_ADDRESS,
        Attributes.RESIDENT_COUNTRY,
        Attributes.RESIDENT_STATE,
        Attributes.RESIDENT_CITY,
        Attributes.RESIDENT_POSTAL_CODE,
        Attributes.RESIDENT_STREET,
        Attributes.RESIDENT_HOUSE_NUMBER,

        Attributes.GENDER,
        Attributes.SEX,
        Attributes.NATIONALITY,

        Attributes.ISSUANCE_DATE,
        Attributes.EXPIRY_DATE,
        Attributes.ISSUING_AUTHORITY,
        Attributes.DOCUMENT_NUMBER,
        Attributes.ADMINISTRATIVE_NUMBER,
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
        Attributes.AGE_OVER_18,
        Attributes.ISSUANCE_DATE,
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

        /** Additional current age attestations: Attesting whether the PID User is currently over 12 years old. */
        const val AGE_OVER_12 = "age_over_12"

        /** Additional current age attestations: Attesting whether the PID User is currently over 14 years old. */
        const val AGE_OVER_14 = "age_over_14"

        /** Additional current age attestations: Attesting whether the PID User is currently over 16 years old. */
        const val AGE_OVER_16 = "age_over_16"

        /** Attesting whether the User to whom the person identification data relates is currently an adult (true) or a minor (false). */
        const val AGE_OVER_18 = "age_over_18"

        /** Additional current age attestations: Attesting whether the PID User is currently over 21 years old. */
        const val AGE_OVER_21 = "age_over_21"

        /** The current age of the User to whom the person identification data relates in years.. */
        const val AGE_IN_YEARS = "age_in_years"

        /** The year when the PID User was born. */
        const val AGE_BIRTH_YEAR = "age_birth_year"

        /** Last name(s) or surname(s) of the User to whom the person identification data relates at the time of birth. */
        const val FAMILY_NAME_BIRTH = "family_name_birth"

        /** First name(s), including middle name(s), of the User to whom the person identification data relates at the time of birth. */
        const val GIVEN_NAME_BIRTH = "given_name_birth"

        /** The country as an alpha-2 country code as specified in ISO 3166-1, or the state, province, district, or
         * local area or the municipality, city, town, or village where the user to whom the person identification data
         * relates was born. */
        const val BIRTH_PLACE = "birth_place"

        /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
        @Deprecated("Removed in ARF 1.5.0")
        const val BIRTH_COUNTRY = "birth_country"

        /** The state, province, district, or local area where the PID User was born. */
        @Deprecated("Removed in ARF 1.5.0")
        const val BIRTH_STATE = "birth_state"

        /** The municipality, city, town, or village where the PID User was born. */
        @Deprecated("Removed in ARF 1.5.0")
        const val BIRTH_CITY = "birth_city"

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

        /** PID User’s gender, using a value as defined in ISO/IEC 5218. */
        @Deprecated("Removed in ARF 1.5.0", ReplaceWith("SEX"))
        const val GENDER = "gender"

        /** Values shall be one of the following: 0 = not known; 1 = male; 2 = female; 3 = other; 4 = inter;
         * 5 = diverse; 6 = open; 9 = not applicable. For values 0, 1, 2 and 9, ISO/IEC 5218 applies. */
        const val SEX = "sex"

        // TODO one or more
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

        /** A number assigned by the PID Provider for audit control or other purposes. */
        @Deprecated("Removed in ARF 1.5.0")
        const val ADMINISTRATIVE_NUMBER = "administrative_number"

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
    }

    /**
     * Maps entries of [Attributes] to entries of [SdJwtAttributes]
     */
    @Suppress("DEPRECATION")
    val mapIsoToSdJwtAttributes = mapOf(
        Attributes.FAMILY_NAME to SdJwtAttributes.FAMILY_NAME,
        Attributes.GIVEN_NAME to SdJwtAttributes.GIVEN_NAME,
        Attributes.BIRTH_DATE to SdJwtAttributes.BIRTH_DATE,
        Attributes.AGE_OVER_12 to SdJwtAttributes.AGE_EQUAL_OR_OVER_12,
        Attributes.AGE_OVER_14 to SdJwtAttributes.AGE_EQUAL_OR_OVER_14,
        Attributes.AGE_OVER_16 to SdJwtAttributes.AGE_EQUAL_OR_OVER_16,
        Attributes.AGE_OVER_18 to SdJwtAttributes.AGE_EQUAL_OR_OVER_18,
        Attributes.AGE_OVER_21 to SdJwtAttributes.AGE_EQUAL_OR_OVER_21,
        Attributes.AGE_IN_YEARS to SdJwtAttributes.AGE_IN_YEARS,
        Attributes.AGE_BIRTH_YEAR to SdJwtAttributes.AGE_BIRTH_YEAR,
        Attributes.FAMILY_NAME_BIRTH to SdJwtAttributes.FAMILY_NAME_BIRTH,
        Attributes.GIVEN_NAME_BIRTH to SdJwtAttributes.GIVEN_NAME_BIRTH,
        Attributes.BIRTH_PLACE to SdJwtAttributes.PLACE_OF_BIRTH_LOCALITY,
        Attributes.BIRTH_COUNTRY to SdJwtAttributes.PLACE_OF_BIRTH_COUNTRY,
        Attributes.BIRTH_STATE to SdJwtAttributes.PLACE_OF_BIRTH_REGION,
        Attributes.BIRTH_CITY to SdJwtAttributes.PLACE_OF_BIRTH_LOCALITY,
        Attributes.RESIDENT_ADDRESS to SdJwtAttributes.ADDRESS_FORMATTED,
        Attributes.RESIDENT_COUNTRY to SdJwtAttributes.ADDRESS_COUNTRY,
        Attributes.RESIDENT_STATE to SdJwtAttributes.ADDRESS_REGION,
        Attributes.RESIDENT_CITY to SdJwtAttributes.ADDRESS_LOCALITY,
        Attributes.RESIDENT_POSTAL_CODE to SdJwtAttributes.ADDRESS_POSTAL_CODE,
        Attributes.RESIDENT_STREET to SdJwtAttributes.ADDRESS_STREET,
        Attributes.RESIDENT_HOUSE_NUMBER to SdJwtAttributes.ADDRESS_HOUSE_NUMBER,
        Attributes.SEX to SdJwtAttributes.GENDER,
        Attributes.NATIONALITY to SdJwtAttributes.NATIONALITIES,
        Attributes.ISSUANCE_DATE to SdJwtAttributes.ISSUANCE_DATE,
        Attributes.EXPIRY_DATE to SdJwtAttributes.EXPIRY_DATE,
        Attributes.ISSUING_AUTHORITY to SdJwtAttributes.ISSUING_AUTHORITY,
        Attributes.DOCUMENT_NUMBER to SdJwtAttributes.DOCUMENT_NUMBER,
        Attributes.ADMINISTRATIVE_NUMBER to SdJwtAttributes.ADMINISTRATIVE_NUMBER,
        Attributes.ISSUING_COUNTRY to SdJwtAttributes.ISSUING_COUNTRY,
        Attributes.ISSUING_JURISDICTION to SdJwtAttributes.ISSUING_JURISDICTION,
        Attributes.PERSONAL_ADMINISTRATIVE_NUMBER to SdJwtAttributes.PERSONAL_ADMINISTRATIVE_NUMBER,
        Attributes.PORTRAIT to SdJwtAttributes.PORTRAIT,
        Attributes.EMAIL_ADDRESS to SdJwtAttributes.EMAIL,
        Attributes.MOBILE_PHONE_NUMBER to SdJwtAttributes.PHONE_NUMBER,
        Attributes.TRUST_ANCHOR to SdJwtAttributes.TRUST_ANCHOR,
        Attributes.LOCATION_STATUS to SdJwtAttributes.LOCATION_STATUS,
    )

    /**
     * Maps entries of [Attributes] to entries of [SdJwtAttributes],
     * but only those attribute names that change (from ISO to SD-JWT)
     */
    @Suppress("DEPRECATION")
    val mapIsoToSdJwtAttributesDifferences = mapOf(
        Attributes.BIRTH_DATE to SdJwtAttributes.BIRTH_DATE,
        Attributes.AGE_OVER_12 to SdJwtAttributes.AGE_EQUAL_OR_OVER_12,
        Attributes.AGE_OVER_14 to SdJwtAttributes.AGE_EQUAL_OR_OVER_14,
        Attributes.AGE_OVER_16 to SdJwtAttributes.AGE_EQUAL_OR_OVER_16,
        Attributes.AGE_OVER_18 to SdJwtAttributes.AGE_EQUAL_OR_OVER_18,
        Attributes.AGE_OVER_21 to SdJwtAttributes.AGE_EQUAL_OR_OVER_21,
        Attributes.FAMILY_NAME_BIRTH to SdJwtAttributes.FAMILY_NAME_BIRTH,
        Attributes.GIVEN_NAME_BIRTH to SdJwtAttributes.GIVEN_NAME_BIRTH,
        Attributes.BIRTH_PLACE to SdJwtAttributes.PLACE_OF_BIRTH_LOCALITY,
        Attributes.BIRTH_COUNTRY to SdJwtAttributes.PLACE_OF_BIRTH_COUNTRY,
        Attributes.BIRTH_STATE to SdJwtAttributes.PLACE_OF_BIRTH_REGION,
        Attributes.BIRTH_CITY to SdJwtAttributes.PLACE_OF_BIRTH_LOCALITY,
        Attributes.RESIDENT_ADDRESS to SdJwtAttributes.ADDRESS_FORMATTED,
        Attributes.RESIDENT_COUNTRY to SdJwtAttributes.ADDRESS_COUNTRY,
        Attributes.RESIDENT_STATE to SdJwtAttributes.ADDRESS_REGION,
        Attributes.RESIDENT_CITY to SdJwtAttributes.ADDRESS_LOCALITY,
        Attributes.RESIDENT_POSTAL_CODE to SdJwtAttributes.ADDRESS_POSTAL_CODE,
        Attributes.RESIDENT_STREET to SdJwtAttributes.ADDRESS_STREET,
        Attributes.RESIDENT_HOUSE_NUMBER to SdJwtAttributes.ADDRESS_HOUSE_NUMBER,
        Attributes.SEX to SdJwtAttributes.GENDER,
        Attributes.NATIONALITY to SdJwtAttributes.NATIONALITIES,
        Attributes.ISSUANCE_DATE to SdJwtAttributes.ISSUANCE_DATE,
        Attributes.EXPIRY_DATE to SdJwtAttributes.EXPIRY_DATE,
        Attributes.EMAIL_ADDRESS to SdJwtAttributes.EMAIL,
        Attributes.MOBILE_PHONE_NUMBER to SdJwtAttributes.PHONE_NUMBER,
    )

    /**
     * Per <https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/pull/160>
     */
    object SdJwtAttributes {
        /** Current last name(s) or surname(s) of the user to whom the person identification data relates. */
        const val FAMILY_NAME = "family_name"

        /** Current first name(s), including middle name(s) where applicable, of the user to whom the person identification data relates. */
        const val GIVEN_NAME = "given_name"

        /** Day, month, and year on which the user to whom the person identification data relates was born. */
        const val BIRTH_DATE = "birthdate"

        /** Additional current age attestations, prefix, see [AgeEqualOrOver] */
        const val PREFIX_AGE_EQUAL_OR_OVER = "age_equal_or_over"

        /** Additional current age attestations: Attesting whether the PID User is currently over 12 years old. */
        const val AGE_EQUAL_OR_OVER_12 = "$PREFIX_AGE_EQUAL_OR_OVER.12"

        /** Additional current age attestations: Attesting whether the PID User is currently over 14 years old. */
        const val AGE_EQUAL_OR_OVER_14 = "$PREFIX_AGE_EQUAL_OR_OVER.14"

        /** Additional current age attestations: Attesting whether the PID User is currently over 16 years old. */
        const val AGE_EQUAL_OR_OVER_16 = "$PREFIX_AGE_EQUAL_OR_OVER.16"

        /** Attesting whether the User to whom the person identification data relates is currently an adult (true) or a minor (false). */
        const val AGE_EQUAL_OR_OVER_18 = "$PREFIX_AGE_EQUAL_OR_OVER.18"

        /** Additional current age attestations: Attesting whether the PID User is currently over 21 years old. */
        const val AGE_EQUAL_OR_OVER_21 = "$PREFIX_AGE_EQUAL_OR_OVER.21"

        object AgeEqualOrOver {
            /** Additional current age attestations: Attesting whether the PID User is currently over 12 years old. */
            const val EQUAL_OR_OVER_12 = "12"

            /** Additional current age attestations: Attesting whether the PID User is currently over 14 years old. */
            const val EQUAL_OR_OVER_14 = "14"

            /** Additional current age attestations: Attesting whether the PID User is currently over 16 years old. */
            const val EQUAL_OR_OVER_16 = "16"

            /** Attesting whether the User to whom the person identification data relates is currently an adult (true) or a minor (false). */
            const val EQUAL_OR_OVER_18 = "18"

            /** Additional current age attestations: Attesting whether the PID User is currently over 21 years old. */
            const val EQUAL_OR_OVER_21 = "21"
        }

        /** The current age of the User to whom the person identification data relates in years. */
        const val AGE_IN_YEARS = "age_in_years"

        /** The year when the User to whom the person identification data relates was born. */
        const val AGE_BIRTH_YEAR = "age_birth_year"

        /** Last name(s) or surname(s) of the User to whom the person identification data relates at the time of birth. */
        const val FAMILY_NAME_BIRTH = "birth_family_name"

        /** First name(s), including middle name(s), of the User to whom the person identification data relates at the time of birth. */
        const val GIVEN_NAME_BIRTH = "birth_given_name"

        /** Place of birth prefix, see [PlaceOfBirth] */
        const val PREFIX_PLACE_OF_BIRTH = "place_of_birth"

        /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
        @Deprecated("Removed in ARF 1.5.0")
        const val PLACE_OF_BIRTH_COUNTRY = "$PREFIX_PLACE_OF_BIRTH.country"

        /** The state, province, district, or local area where the PID User was born. */
        @Deprecated("Removed in ARF 1.5.0")
        const val PLACE_OF_BIRTH_REGION = "$PREFIX_PLACE_OF_BIRTH.region"

        /** The country as an alpha-2 country code as specified in ISO 3166-1, or the state, province, district, or
         *  local area or the municipality, city, town, or village where the user to whom the person identification
         *  data relates was born. */
        const val PLACE_OF_BIRTH_LOCALITY = "$PREFIX_PLACE_OF_BIRTH.locality"

        object PlaceOfBirth {
            /** The country where the PID User was born, as an Alpha-2 country code as specified in ISO 3166-1. */
            @Deprecated("Removed in ARF 1.5.0")
            const val COUNTRY = "country"

            /** The state, province, district, or local area where the PID User was born. */
            @Deprecated("Removed in ARF 1.5.0")
            const val REGION = "region"

            /** The country as an alpha-2 country code as specified in ISO 3166-1, or the state, province, district, or
             *  local area or the municipality, city, town, or village where the user to whom the person identification
             *  data relates was born. */
            const val LOCALITY = "locality"
        }

        /** Address prefix, see [Address] */
        const val PREFIX_ADDRESS = "address"

        /**
         * The full address of the place where the PID User currently resides and/or can be contacted
         * (street name, house number, city etc.).
         */
        const val ADDRESS_FORMATTED = "$PREFIX_ADDRESS.formatted"

        /** The country where the PID User currently resides, as an Alpha-2 country code as specified in ISO 3166-1. */
        const val ADDRESS_COUNTRY = "$PREFIX_ADDRESS.country"

        /** The state, province, district, or local area where the PID User currently resides. */
        const val ADDRESS_REGION = "$PREFIX_ADDRESS.region"

        /** The municipality, city, town, or village where the PID User currently resides. */
        const val ADDRESS_LOCALITY = "$PREFIX_ADDRESS.locality"

        /** Postal code of the place where the PID User currently resides. */
        const val ADDRESS_POSTAL_CODE = "$PREFIX_ADDRESS.postal_code"

        /** The name of the street where the PID User currently resides. */
        const val ADDRESS_STREET = "$PREFIX_ADDRESS.street_address"

        /** The house number where the PID User currently resides, including any affix or suffix. */
        const val ADDRESS_HOUSE_NUMBER = "$PREFIX_ADDRESS.house_number"

        object Address {
            /**
             * The full address of the place where the user to whom the person identification data relates currently
             * resides or can be contacted (street name, house number, city etc.).
             */
            const val FORMATTED = "formatted"

            /** The country where the user to whom the person identification data relates currently resides, as an
             * alpha-2 country code as specified in ISO 3166-1. */
            const val COUNTRY = "country"

            /** The state, province, district, or local area where the user to whom the person identification data
             * relates currently resides. */
            const val REGION = "region"

            /** The municipality, city, town, or village where the user to whom the person identification data relates
             *  currently resides. */
            const val LOCALITY = "locality"

            /** The postal code of the place where the user to whom the person identification data relates currently
             * resides. */
            const val POSTAL_CODE = "postal_code"

            /** The name of the street where the user to whom the person identification data relates currently resides. */
            const val STREET = "street_address"

            /** The house number where the user to whom the person identification data relates currently resides,
             *  including any affix or suffix. */
            const val HOUSE_NUMBER = "house_number"
        }

        /** PID User’s gender, using a string value like `female`, `male`, or custom text values. */
        const val GENDER = "gender"

        /** One or more alpha-2 country codes as specified in ISO 3166-1, representing the nationality of the user to
         *  whom the person identification data relates. */
        const val NATIONALITIES = "nationalities"

        /** Date (and if possible time) when the person identification data was issued and/or the administrative validity period of the person identification data began. */
        const val ISSUANCE_DATE = "iat"

        /** Date (and if possible time) when the person identification data will expire. */
        const val EXPIRY_DATE = "exp"

        /**
         * Name of the administrative authority that has issued this PID instance, or
         * the ISO 3166 Alpha-2 country code of the respective Member State if
         * there is no separate authority authorized to issue PIDs.
         */
        const val ISSUING_AUTHORITY = "issuing_authority"

        /** A number for the PID, assigned by the PID Provider. */
        const val DOCUMENT_NUMBER = "document_number"

        /** A number assigned by the PID Provider for audit control or other purposes. */
        @Deprecated("Removed in ARF 1.5.0")
        const val ADMINISTRATIVE_NUMBER = "administrative_number"

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
        const val EMAIL = "email"

        /** Mobile telephone number of the User to whom the person identification data relates, starting with the '+'
         * symbol as the international code prefix and the country code, followed by numbers only. */
        const val PHONE_NUMBER = "phone_number"

        /** This attribute indicates at least the URL at which a machine-readable version of the trust anchor to be used for verifying the PID can be found or looked up */
        const val TRUST_ANCHOR = "trust_anchor"

        /** The location of validity status information on the person identification data where the providers of person identification data revoke person identification data. */
        const val LOCATION_STATUS = "location_status"
    }

}
