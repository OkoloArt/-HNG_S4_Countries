package com.example.countries.data.remote.dto.country

import com.google.gson.annotations.SerializedName

data class Idd(
    @SerializedName("root")
    val root: String? ,
    @SerializedName("suffixes")
    val suffixes: List<String>?
)