package com.example.ipapp.data.remote.repository

import com.example.ipapp.common.Resource
import com.example.ipapp.domain.model.IpAddress
import kotlinx.coroutines.flow.Flow

interface IpAddressRepository {
    suspend fun getIpAddress(ipAddress:String):Flow<Resource<IpAddress>>
    suspend fun getAllIpAddresses():Flow<Resource<List<IpAddress>>>
}