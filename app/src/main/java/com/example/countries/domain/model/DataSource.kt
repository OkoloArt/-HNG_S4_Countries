package com.example.countries.domain.model

class DataSource {

    fun loadLanguages(): List<LanguageModel> {
        return listOf(
                LanguageModel("Kotlin" , "") ,
                LanguageModel("Android" , "") ,
                LanguageModel("Git" , "") ,
                LanguageModel("Java" , "")
        )
    }
}