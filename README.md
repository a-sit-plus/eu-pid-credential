# ID Austria Credential
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-brightgreen.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform--mobile-orange.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
![Java](https://img.shields.io/badge/java-11-blue.svg?logo=OPENJDK)
[![Maven Central](https://img.shields.io/maven-central/v/at.asitplus.wallet/idacredential)](https://mvnrepository.com/artifact/at.asitplus.wallet/eupidcredential/)

Use data provided by EU Wallets as a W3C VC, or ISO 18013-5 Credential, with the help of [KMM VC Lib](https://github.com/a-sit-plus/kmm-vc-library).

Be sure to call `at.asitplus.wallet.eupid.Initializer.initWithVcLib` first thing in your application.

See <https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-06-pid-rulebook.md> for a list of attributes. These are implemented:
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

## Changelog

Release 1.0.0:
 - Initial release, based on PID Rule Book 1.0.0 from November 2023