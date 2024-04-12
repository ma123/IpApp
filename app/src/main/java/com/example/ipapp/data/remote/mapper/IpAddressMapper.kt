package com.example.ipapp.data.remote.mapper

import com.example.ipapp.data.local.IpAddressEntity
import com.example.ipapp.data.remote.dto.IpAddressDto
import com.example.ipapp.domain.model.IpAddress

fun IpAddressDto.toIpAddress(): IpAddress {
    return IpAddress(
        asn = asn,
        city = city,
        continentCode = continentCode,
        country = country,
        countryArea = countryArea,
        countryCallingCode = countryCallingCode,
        countryCapital = countryCapital,
        countryCode = countryCode,
        countryCodeIso3 = countryCodeIso3,
        countryName = countryName,
        countryPopulation = countryPopulation,
        countryTld = countryTld,
        currency = currency,
        currencyName = currencyName,
        inEu = inEu,
        ip = ip,
        languages = languages,
        latitude = latitude,
        longitude = longitude,
        network = network,
        org = org,
        postal = postal,
        region = region,
        regionCode = regionCode,
        timezone = timezone,
        utcOffset = utcOffset,
        version = version
    )
}

fun IpAddress.toIpAddressEntity(): IpAddressEntity {
    return IpAddressEntity(
        asn = asn,
        city = city,
        continentCode = continentCode,
        country = country,
        countryArea = countryArea,
        countryCallingCode = countryCallingCode,
        countryCapital = countryCapital,
        countryCode = countryCode,
        countryCodeIso3 = countryCodeIso3,
        countryName = countryName,
        countryPopulation = countryPopulation,
        countryTld = countryTld,
        currency = currency,
        currencyName = currencyName,
        inEu = inEu,
        ip = ip,
        languages = languages,
        latitude = latitude,
        longitude = longitude,
        network = network,
        org = org,
        postal = postal,
        region = region,
        regionCode = regionCode,
        timezone = timezone,
        utcOffset = utcOffset,
        version = version
    )
}

fun IpAddressEntity.toIpAddress(): IpAddress {
    return IpAddress(
        asn = asn,
        city = city,
        continentCode = continentCode,
        country = country,
        countryArea = countryArea,
        countryCallingCode = countryCallingCode,
        countryCapital = countryCapital,
        countryCode = countryCode,
        countryCodeIso3 = countryCodeIso3,
        countryName = countryName,
        countryPopulation = countryPopulation,
        countryTld = countryTld,
        currency = currency,
        currencyName = currencyName,
        inEu = inEu,
        ip = ip,
        languages = languages,
        latitude = latitude,
        longitude = longitude,
        network = network,
        org = org,
        postal = postal,
        region = region,
        regionCode = regionCode,
        timezone = timezone,
        utcOffset = utcOffset,
        version = version
    )
}