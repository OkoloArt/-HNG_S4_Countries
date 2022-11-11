package com.example.countries.data.remote.dto.country

import com.google.gson.annotations.SerializedName

data class Currencies(
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)