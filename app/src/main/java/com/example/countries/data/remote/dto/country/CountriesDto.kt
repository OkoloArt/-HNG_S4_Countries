package com.example.countries.data.remote.dto.country

data class CountriesDto(
    val area: Double ,
    val capital: List<String> ,
    val car: Car ,
    val coatOfArms: CoatOfArms ,
    val continents: List<String> ,
    val currencies: Currencies ,
    val flags: Flags ,
    val idd: Idd ,
    val maps: Maps ,
    val name: Name ,
    val population: Int ,
    val region: String ,
    val subregion: String ,
    val timezones: List<String>
)
