package com.example.ipapp.di

import android.app.Application
import androidx.room.Room
import com.example.ipapp.common.Constants
import com.example.ipapp.data.local.IpAddressDatabase
import com.example.ipapp.data.remote.IpApi
import com.example.ipapp.data.remote.repository.IpAddressRepository
import com.example.ipapp.domain.repository.IpAddressRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideIpApi(): IpApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(IpApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIpAddressRepository(api: IpApi, db: IpAddressDatabase): IpAddressRepository {
        return IpAddressRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideIpAddressDatabase(app: Application): IpAddressDatabase {
        return Room.databaseBuilder(
            app, IpAddressDatabase::class.java, "ipaddress_db"
        ).build()
    }
}