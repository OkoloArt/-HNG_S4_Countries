package com.example.countries.ui.filter_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.domain.model.FilterChildData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(): ViewModel(){

    private val _filter = MutableLiveData<List<FilterChildData>>()
    val filter: LiveData<List<FilterChildData>> = _filter

    init
    {
        _filter.value = emptyList()
    }

    fun filterWords(list: List<FilterChildData>){
        _filter.value = list
    }
}