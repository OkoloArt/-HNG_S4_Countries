package com.example.countries.data.repository

import com.example.countries.data.remote.CountriesApi
import com.example.countries.data.remote.dto.country.CountriesDto
import com.example.countries.domain.repository.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val api : CountriesApi
): CountriesRepository
{
    override suspend fun getCountries(): List<CountriesDto>
    {
        return api.getCountries().body()!!
    }
}