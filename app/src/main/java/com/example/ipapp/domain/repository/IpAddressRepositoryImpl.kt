package com.example.ipapp.domain.repository

import com.example.ipapp.common.Resource
import com.example.ipapp.data.local.IpAddressDatabase
import com.example.ipapp.data.remote.IpApi
import com.example.ipapp.data.remote.dto.IpAddressDto
import com.example.ipapp.data.remote.mapper.toIpAddress
import com.example.ipapp.data.remote.repository.IpAddressRepository
import com.example.ipapp.domain.model.IpAddress
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class IpAddressRepositoryImpl @Inject constructor(
    private val api: IpApi,
    private val db: IpAddressDatabase
) : IpAddressRepository {

    override suspend fun getIpAddress(ipAddress: String): Flow<Resource<IpAddress>> {
        return flow {
            try {
                emit(Resource.Loading())
                val ipAddress = api.getIpAddressData(ipAddress)
                emit(Resource.Success(ipAddress.toIpAddress()))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun getAllIpAddresses(): Flow<Resource<List<IpAddress>>> {
        return flow {
            try {
                val localIpList = db.dao.getIpAddress()

                emit(Resource.Success(data = localIpList.map {
                    it.toIpAddress()
                }))
            } catch (e: HttpException) {
                Timber.d("### http error")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch (e: IOException) {
                Timber.d("### io exception")
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }
}