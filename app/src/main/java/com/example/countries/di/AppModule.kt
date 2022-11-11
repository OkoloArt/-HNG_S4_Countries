package com.example.countries.di

import android.content.Context
import com.example.countries.common.Constants
import com.example.countries.data.remote.CountriesApi
import com.example.countries.data.repository.CountriesRepositoryImpl
import com.example.countries.domain.repository.CountriesRepository
import com.example.countries.utils.ConnectivityObserver
import com.example.countries.utils.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    @Provides
    @Singleton
    fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(api: CountriesApi):CountriesRepository{
        return CountriesRepositoryImpl(api)
    }

    @Provides
    @Singleton
     fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver
    {
         return NetworkConnectivityObserver(context)
     }
}