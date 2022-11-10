package com.example.countries.di

import com.example.countries.common.Constants
import com.example.countries.data.remote.CountriesApi
import com.example.countries.data.repository.CountriesRepositoryImpl
import com.example.countries.domain.repository.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}