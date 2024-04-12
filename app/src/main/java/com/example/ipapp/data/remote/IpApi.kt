package com.example.ipapp.data.remote

import com.example.ipapp.data.remote.dto.IpAddressDto
import retrofit2.http.GET
import retrofit2.http.Path

interface IpApi {
    @GET("/{ipAddress}/json/")
    suspend fun getIpAddressData(@Path("ipAddress") ipAddress: String): IpAddressDto
}