# EU PID Credential

<div align="center">
 
[![A-SIT Plus Official](https://raw.githubusercontent.com/a-sit-plus/a-sit-plus.github.io/709e802b3e00cb57916cbb254ca5e1a5756ad2a8/A-SIT%20Plus_%20official_opt.svg)](https://plus.a-sit.at/open-source.html)
[![Powered by VC-K](https://img.shields.io/badge/VC--K-powered-8A2BE2?logo=data%3Aimage%2Fsvg%2Bxml%3Bbase64%2CPHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCA4LjAzIDkuNSI+PGcgZmlsbD0iIzhhMmJlMiIgZm9udC1mYW1pbHk9IlZBTE9SQU5UIiBmb250LXNpemU9IjEyLjciIHRleHQtYW5jaG9yPSJtaWRkbGUiPjxwYXRoIGQ9Ik01OS42NCAyMjIuMTNxMC0uOTguMzYtMS44Mi4zNy0uODQuOTgtMS40Ni42Mi0uNjIgMS40Ni0uOTYuODMtLjM2IDEuOC0uMzUgMS4wMy4wMiAxLjkuNDIuODcuNCAxLjUgMS4xMi4wNC4wNS4wMy4xMSAwIC4wNy0uMDUuMWwtMSAuODZxLS4wNi4wMy0uMTIuMDN0LS4xLS4wNnEtLjQyLS40OC0xLS43Ni0uNTYtLjMtMS4yMi0uMjgtLjYuMDEtMS4xMy4yNy0uNTQuMjQtLjkzLjY3LS40LjQyLS42Mi45OC0uMjMuNTYtLjIzIDEuMiAwIC42My4yNCAxLjE4LjI0LjU2LjY1Ljk4LjQuNDIuOTQuNjYuNTMuMjMgMS4xNC4yMy42My0uMDEgMS4yLS4zLjU1LS4yNy45Ni0uNzUuMDQtLjA1LjEtLjA1LjA2LS4wMi4xMS4wM2wxIC44NnEuMDYuMDMuMDYuMS4wMS4wNi0uMDMuMTEtLjY0LjczLTEuNTMgMS4xNC0uOS40MS0xLjk1LjQtLjk1IDAtMS43OS0uMzYtLjgyLS4zNy0xLjQzLS45OS0uNjEtLjYzLS45NS0xLjQ4LS4zNS0uODUtLjM1LTEuODN6IiBzdHlsZT0iLWlua3NjYXBlLWZvbnQtc3BlY2lmaWNhdGlvbjpWQUxPUkFOVDt0ZXh0LWFsaWduOmNlbnRlciIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTU5LjY0IC0yMTcuNDIpIi8+PHBhdGggZD0iTTY2LjIxIDIyMS4zNWgxLjNjLjEgMCAuMTYuMDYuMTYuMTd2MS4zOGMwIC4xMS0uMDUuMTctLjE2LjE3aC0xLjNjLS4xIDAtLjE2LS4wNi0uMTYtLjE3di0xLjM4YzAtLjExLjA1LS4xNy4xNi0uMTd6IiBsZXR0ZXItc3BhY2luZz0iLTMuMTIiIHN0eWxlPSItaW5rc2NhcGUtZm9udC1zcGVjaWZpY2F0aW9uOlZBTE9SQU5UO3RleHQtYWxpZ246Y2VudGVyIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtNTkuNjQgLTIxNy40MikiLz48L2c+PC9zdmc+&logoColor=white&labelColor=white)](https://github.com/a-sit-plus/vck)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-brightgreen.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform--mobile-orange.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlin](https://img.shields.io/badge/kotlin-2.2.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
![Java](https://img.shields.io/badge/java-17-blue.svg?logo=OPENJDK)
[![Maven Central](https://img.shields.io/maven-central/v/at.asitplus.wallet/eupidcredential)](https://mvnrepository.com/artifact/at.asitplus.wallet/eupidcredential/)

</div>

Use data provided by EU Wallets as a W3C VC, SD-JWT (prior to ARF 1.8.0), or ISO 18013-5 credential, with the help of [VC-K](https://github.com/a-sit-plus/vck).

Be sure to call `at.asitplus.wallet.eupid.Initializer.initWithVCK()` first thing in your application.

See [ARF PID Rulebook](https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/blob/main/docs/annexes/annex-3/annex-3.01-pid-rulebook.md) for a list of attributes.

These are implemented for ISO:
- `family_name`
- `given_name`
- `birth_date`
- `age_over_12`
- `age_over_13`
- `age_over_14`
- `age_over_16`
- `age_over_18`
- `age_over_21`
- `age_over_25`
- `age_over_60`
- `age_over_62`
- `age_over_65`
- `age_over_68`
- `age_in_years`
- `age_birth_year`
- `family_name_birth`
- `given_name_birth`
- `birth_place`
- `birth_country` (removed in ARF 1.5.0)
- `birth_state` (removed in ARF 1.5.0)
- `birth_city` (removed in ARF 1.5.0)
- `resident_address`
- `resident_country`
- `resident_state`
- `resident_city`
- `resident_postal_code`
- `resident_street`
- `resident_house_number`
- `sex`
- `nationality`
- `issuance_date`
- `expiry_date`
- `issuing_authority`
- `document_number`
- `administrative_number` (removed in ARF 1.5.0)
- `issuing_country`
- `issuing_jurisdiction`
- `personal_administrative_number`
- `portrait`
- `portrait_capture_date`
- `email_address`
- `mobile_phone_number`
- `trust_anchor`
- `location_status`

## Changelog

Release 3.2.0:
- VC-K 5.8.0
- Kotlin 2.2.0
- kotlinx-datetime 0.7.1

Release 3.1.0:
 - Update to VC-K 5.7.0 
 - `expiry_date` and `issuance_date` are of type `LocalDateOrInstant` from vc-k acc. to spec "Date (and if possible time) ..."

Release 3.0.1:
 - Deprecate SD-JWT claim name mapping introduced in 2.3.0, please migrate to `at.asitplus.wallet:eupidcredential-sdjwt`, see <https://github.com/a-sit-plus/eu-pid-credential-sdjwt/>
 - Add additional claims `age_over_13`, `age_over_25`, `age_over_60`, `age_over_62`, `age_over_65`, `age_over_68`, `portrait_capture_date`

Release 3.0.0:
 - Update to ARF 1.5.0, deprecating removed claims, adding new claims, changing `nationality` from single element to collection

Release 2.3.2:
 - VC-K 5.3.0

Release 2.3.1:
- Add additional claims `personal_administrative_number` and `portrait` (acc. to Implementing Act)

Release 2.3.0:
- Implement mapping to SD-JWT claim names according to <https://github.com/eu-digital-identity-wallet/eudi-doc-architecture-and-reference-framework/pull/160>

Release 2.2.3:
 - VC-K 5.2.2
 - Add additional claims: `age_over_12`, `age_over_14`, `age_over_16`, `age_over_21`

Release 2.2.2:
 - VC-K 5.2.1
 - Kotlin 2.1.0

Release 2.2.1:
 - Fix serialization in ISO format

Release 2.2.0:
 - Update to VC-K 5.0.0

Release 2.1.3:
 - Update to VC-K 4.1.0

Release 2.1.2:
 - Update to `vclib` 4.0.0

Release 2.1.1:
 - Fix required claim names in `EuPidScheme` to contain `age_over_18` and `issuing_country`

Release 2.1.0:
 - Update to `vclib` 3.8.0
 - Use correct namespace, doc type and SD-JWT type

Release 2.0.2:
 - Update to `vclib` 3.7.0
 - Koltin 2.0.0

Release 2.0.1:
 - Fix publishing, re-releasing 2.0.0

Release 2.0.0:
 - Implement metadata, based on PID Rule Book 1.0.0 from November 2023

Release 1.0.0:
 - Initial release, based on PID Rule Book 1.0.0 from November 2023


<br>

---
<p align="center">
The Apache License does not apply to the logos, (including the A-SIT logo and the VC-K logo) and the project/module name(s)
(even those used only in badges), as these are the sole property of A-SIT/A-SIT Plus GmbH and may not be used
in derivative works without explicit permission! 
</p>

