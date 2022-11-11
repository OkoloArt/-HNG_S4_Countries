package com.example.countries.domain.model

class DataSource {

    fun loadLanguages(): List<LanguageModel> {
        return listOf(
                LanguageModel("Bahasa" , "") ,
                LanguageModel("Deutsch" , "") ,
                LanguageModel("English" , "") ,
                LanguageModel("Espanol" , ""),
                LanguageModel("French" , "") ,
                LanguageModel("Italian" , "") ,
                LanguageModel("Portuguese" , "") ,
                LanguageModel("Bengali" , ""),
                LanguageModel("Turkish" , "") ,
                LanguageModel("Ukrainian" , "") ,
                LanguageModel("Czech" , "") ,

        )
    }
}