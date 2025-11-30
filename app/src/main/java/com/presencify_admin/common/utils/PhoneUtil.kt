package com.presencify_admin.common.utils


import com.google.i18n.phonenumbers.PhoneNumberUtil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.util.Locale


object PhoneUtil {

    fun getCountries(): ImmutableList<Country> {
        val phoneUtil = PhoneNumberUtil.getInstance()
        val countries = phoneUtil.supportedRegions.map { regionCode ->
            Country(
                name = Locale("", regionCode).displayCountry,
                code = regionCode,
                phoneCode = "+${phoneUtil.getCountryCodeForRegion(regionCode)}"
            )
        }
        val sortedCountries = countries.sortedBy { it.name }.toImmutableList()
        return sortedCountries
    }

    fun getCountryByPhoneCode(phoneCode: String): Country {
        var countries = getCountries()
        return countries.find {
            it.phoneCode == phoneCode
        } ?: Country(name = "Unknown",
            code = "-",
            phoneCode = "-")
    }

    fun getIndiaAsDefaultCountry(): Country {
        return Country(
            name = "India",
            code = "IN",
            phoneCode = "+91"
        )
    }

    data class Country(
        val name: String,
        val code: String, // Country code (e.g., "US")
        val phoneCode: String, // Phone code (e.g., "+1")
    )

}
