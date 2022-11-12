package com.example.countries.domain.model

import com.example.countries.common.Constants

data class FilterParentModel(
    val parentTitle:String?=null ,
    var type:Int = Constants.PARENT ,
    var subList : MutableList<FilterChildData> = ArrayList() ,
    var isExpanded:Boolean = false
)