package com.example.ipapp.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import kotlinx.coroutines.flow.Flow

@Dao
interface IpAddressDao {
    @Insert
    suspend fun insertIpAddress(ipAddress: IpAddressEntity)

    @Query("SELECT * FROM ipAddressEntity")
    fun getIpAddress(): List<IpAddressEntity>
}