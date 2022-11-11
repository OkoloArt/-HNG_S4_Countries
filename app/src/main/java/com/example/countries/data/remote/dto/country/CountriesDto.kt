package com.example.countries.data.remote.dto.country

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountriesDto(
    val area: Double? ,
    val capital: List<String>? = emptyList() ,
    val car: Car ,
    val coatOfArms: CoatOfArms? ,
    val continents: List<String>? ,
    @SerializedName("currencies")
    val currencies: HashMap<String, Currencies>?= HashMap(),
    val flags: Flags ,
    @SerializedName("idd")
    val idd: Idd ,
    val name: Name ,
    val population: Int? ,
    val region: String? ,
    val independent: Boolean? ,
    val subregion: String? ,
    @SerializedName("languages")
    val languages: HashMap<String, String>? = HashMap(),
    val timezones: List<String>?
) : Serializable
