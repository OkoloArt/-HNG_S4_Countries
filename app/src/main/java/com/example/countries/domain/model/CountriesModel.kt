package com.example.countries.domain.model

import com.example.countries.data.remote.dto.country.CountriesDto


data class CountriesModel(var name: String, var countriesDto: CountriesDto , var isSection: Boolean)