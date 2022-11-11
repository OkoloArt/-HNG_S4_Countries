package com.example.countries.ui.country_list

import com.example.countries.data.remote.dto.country.CountriesDto

data class CountryListState(
    val isLoading: Boolean = false ,
    val country: List<CountriesDto> = emptyList() ,
    val error: String = ""
)