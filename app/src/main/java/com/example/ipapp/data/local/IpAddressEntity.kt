package com.example.ipapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IpAddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val asn: String? = null,
    val city: String? = null,
    val continentCode: String? = null,
    val country: String? = null,
    val countryArea: Double? = null,
    val countryCallingCode: String? = null,
    val countryCapital: String? = null,
    val countryCode: String? = null,
    val countryCodeIso3: String? = null,
    val countryName: String? = null,
    val countryPopulation: Int? = null,
    val countryTld: String? = null,
    val currency: String? = null,
    val currencyName: String? = null,
    val inEu: Boolean? = null,
    val ip: String? = null,
    val languages: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val network: String? = null,
    val org: String? = null,
    val postal: String? = null,
    val region: String? = null,
    val regionCode: String? = null,
    val timezone: String? = null,
    val utcOffset: String? = null,
    val version: String? = null
)
