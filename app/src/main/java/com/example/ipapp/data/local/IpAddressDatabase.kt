package com.example.ipapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [IpAddressEntity::class],
    version = 1
)
abstract class IpAddressDatabase: RoomDatabase() {
    abstract val dao: IpAddressDao
}