package com.example.countries.domain.repository

import com.example.countries.data.remote.dto.country.CountriesDto

interface CountriesRepository
{
    suspend fun getCountries() : List<CountriesDto>

}