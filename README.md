# EU PID Credential
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-brightgreen.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform--mobile-orange.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlin](https://img.shields.io/badge/kotlin-2.0.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
![Java](https://img.shields.io/badge/java-17-blue.svg?logo=OPENJDK)
[![Maven Central](https://img.shields.io/maven-central/v/at.asitplus.wallet/eupidcredential)](https://mvnrepository.com/artifact/at.asitplus.wallet/eupidcredential/)

Use data provided by EU Wallets as a W3C VC, or ISO 18013-5 Credential, with the help of [KMM VC Lib](https://github.com/a-sit-plus/kmm-vc-library).

Be sure to call `at.asitplus.wallet.eupid.Initializer.initWithVcLib` first thing in your application.

See [PID Rule Book](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-06-pid-rulebook.md) for a list of attributes. These are implemented:
- `family_name`
- `given_name`
- `birth_date`
- `age_over_18`
- `age_in_years`
- `age_birth_year`
- `family_name_birth`
- `given_name_birth`
- `birth_place`
- `birth_country`
- `birth_state`
- `birth_city`
- `resident_address`
- `resident_country`
- `resident_state`
- `resident_city`
- `resident_postal_code`
- `resident_street`
- `resident_house_number`
- `gender`
- `nationality`
- `issuance_date`
- `expiry_date`
- `issuing_authority`
- `document_number`
- `administrative_number`
- `issuing_country`
- `issuing_jurisdiction`

## Changelog

Release 2.1.0:
 - Update to `vclib` 3.8.0

Release 2.0.2:
 - Update to `vclib` 3.7.0
 - Koltin 2.0.0

Release 2.0.1:
 - Fix publishing, re-releasing 2.0.0

Release 2.0.0:
 - Implement metadata, based on PID Rule Book 1.0.0 from November 2023

Release 1.0.0:
 - Initial release, based on PID Rule Book 1.0.0 from November 2023
