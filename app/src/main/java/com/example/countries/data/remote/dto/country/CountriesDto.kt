package com.example.countries.data.remote.dto.country

import java.io.Serializable

data class CountriesDto(
    val area: Double ,
    val capital: List<String> ,
    val car: Car ,
    val coatOfArms: CoatOfArms ,
    val continents: List<String> ,
  //  val currencies: Currencies ,
    val flags: Flags ,
    val idd: Idd ,
    val name: Name ,
    val population: Int ,
    val region: String ,
    val subregion: String ,
//    val language: Language ,
    val timezones: List<String>
) : Serializable
