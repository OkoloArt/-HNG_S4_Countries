package com.example.countries.data.remote

import com.example.countries.data.remote.dto.country.CountriesDto
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi
{
    @GET("all")
    suspend fun getCountries(): Response<List<CountriesDto>>
}