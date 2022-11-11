package com.example.countries.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.common.Resource
import com.example.countries.domain.use_case.GetCountriesUseCase
import com.example.countries.ui.country_list.CountryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase ,
) : ViewModel()
{
    private val _state = MutableStateFlow(CountryListState())
    val state: StateFlow<CountryListState> = _state

    init
    {
        getCountries()
    }

    private fun getCountries()
    {
        getCountriesUseCase().onEach { result ->
            when (result)
            {
                is Resource.Success ->
                {
                    _state.value = CountryListState(country = result.data ?: emptyList())
                }
                is Resource.Error ->
                {
                    _state.value = CountryListState(
                            error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading ->
                {
                    _state.value = CountryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}