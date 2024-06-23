package at.asitplus.wallet.eupid

import at.asitplus.wallet.lib.data.ConstantIndex
import at.asitplus.wallet.lib.data.ConstantIndex.CredentialRepresentation.*
import at.asitplus.wallet.lib.data.SchemaIndex


/**
 * PID scheme according to [EU PID Rule Book, v1.0.0 from November 2023](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-06-pid-rulebook.md)
 */
object EuPidScheme : ConstantIndex.CredentialScheme {
    override val schemaUri: String = "${SchemaIndex.BASE}/schemas/1.0.0/eupid.json"
    override val vcType: String = "EuPid2023"
    override val isoNamespace: String = "eu.europa.ec.eudi.pid.1"
    override val isoDocType: String = "eu.europa.ec.eudi.pid.1"
    override val sdJwtType: String = "urn:eu.europa.ec.eudi:pid:1"
    override val supportedRepresentations: Collection<ConstantIndex.CredentialRepresentation> =
        listOf(PLAIN_JWT, SD_JWT, ISO_MDOC)

    override val claimNames: Collection<String> = listOf(
        Attributes.FAMILY_NAME,
        Attributes.GIVEN_NAME,
        Attributes.BIRTH_DATE,

        Attributes.AGE_OVER_18,
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
        Attributes.NATIONALITY,

        Attributes.ISSUANCE_DATE,
        Attributes.EXPIRY_DATE,
        Attributes.ISSUING_AUTHORITY,
        Attributes.DOCUMENT_NUMBER,
        Attributes.ADMINISTRATIVE_NUMBER,
        Attributes.ISSUING_COUNTRY,
        Attributes.ISSUING_JURISDICTION,
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
        const val FAMILY_NAME = "family_name"
        const val GIVEN_NAME = "given_name"
        const val BIRTH_DATE = "birth_date"

        const val AGE_OVER_18 = "age_over_18"
        const val AGE_IN_YEARS = "age_in_years"
        const val AGE_BIRTH_YEAR = "age_birth_year"
        const val FAMILY_NAME_BIRTH = "family_name_birth"
        const val GIVEN_NAME_BIRTH = "given_name_birth"

        const val BIRTH_PLACE = "birth_place"
        const val BIRTH_COUNTRY = "birth_country"
        const val BIRTH_STATE = "birth_state"
        const val BIRTH_CITY = "birth_city"

        const val RESIDENT_ADDRESS = "resident_address"
        const val RESIDENT_COUNTRY = "resident_country"
        const val RESIDENT_STATE = "resident_state"
        const val RESIDENT_CITY = "resident_city"
        const val RESIDENT_POSTAL_CODE = "resident_postal_code"
        const val RESIDENT_STREET = "resident_street"
        const val RESIDENT_HOUSE_NUMBER = "resident_house_number"

        const val GENDER = "gender"
        const val NATIONALITY = "nationality"

        const val ISSUANCE_DATE = "issuance_date"
        const val EXPIRY_DATE = "expiry_date"
        const val ISSUING_AUTHORITY = "issuing_authority"
        const val DOCUMENT_NUMBER = "document_number"
        const val ADMINISTRATIVE_NUMBER = "administrative_number"
        const val ISSUING_COUNTRY = "issuing_country"
        const val ISSUING_JURISDICTION = "issuing_jurisdiction"
    }

    /**
     * Maps names from [Attributes] to claim names used in SD-JWT
     */
    val claimNameMap = mapOf(
        Attributes.FAMILY_NAME to Attributes.FAMILY_NAME,
        Attributes.GIVEN_NAME to Attributes.GIVEN_NAME,
        Attributes.BIRTH_DATE to "birthdate",

        Attributes.AGE_OVER_18 to "age_equal_or_over/18",
        Attributes.AGE_IN_YEARS to Attributes.AGE_IN_YEARS,
        Attributes.AGE_BIRTH_YEAR to Attributes.AGE_BIRTH_YEAR,
        Attributes.FAMILY_NAME_BIRTH to "birth_family_name",
        Attributes.GIVEN_NAME_BIRTH to "birth_given_name",
        Attributes.BIRTH_PLACE to "place_of_birth/locality",
        Attributes.BIRTH_COUNTRY to "place_of_birth/country",
        Attributes.BIRTH_STATE to "place_of_birth/region",
        Attributes.BIRTH_CITY to "place_of_birth/locality",

        Attributes.RESIDENT_ADDRESS to "address/formatted",
        Attributes.RESIDENT_COUNTRY to "address/country",
        Attributes.RESIDENT_STATE to "address/region",
        Attributes.RESIDENT_CITY to "address/locality",
        Attributes.RESIDENT_POSTAL_CODE to "address/postal_code",
        Attributes.RESIDENT_STREET to "address/street_address",
        Attributes.RESIDENT_HOUSE_NUMBER to "address/house_number",

        Attributes.GENDER to Attributes.GENDER,
        Attributes.NATIONALITY to "nationalities",

        Attributes.ISSUANCE_DATE to "iat",
        Attributes.EXPIRY_DATE to "exp",
        Attributes.ISSUING_AUTHORITY to Attributes.ISSUING_AUTHORITY,
        Attributes.DOCUMENT_NUMBER to Attributes.DOCUMENT_NUMBER,
        Attributes.ADMINISTRATIVE_NUMBER to Attributes.ADMINISTRATIVE_NUMBER,
        Attributes.ISSUING_COUNTRY to Attributes.ISSUING_COUNTRY,
        Attributes.ISSUING_JURISDICTION to Attributes.ISSUING_JURISDICTION,
    )
}
