package com.edu.wiet_admin.common.utils


import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.Locale


object PhoneNumberUtil {

    fun getCountries(): List<Country> {
        val phoneUtil = PhoneNumberUtil.getInstance()
        return phoneUtil.supportedRegions.map { regionCode ->
            Country(
                name = Locale("", regionCode).displayCountry,
                code = regionCode,
                phoneCode = "+${phoneUtil.getCountryCodeForRegion(regionCode)}"
            )
        }
    }

    data class Country(
        val name: String,
        val code: String, // Country code (e.g., "US")
        val phoneCode: String // Phone code (e.g., "+1")
    )

}
