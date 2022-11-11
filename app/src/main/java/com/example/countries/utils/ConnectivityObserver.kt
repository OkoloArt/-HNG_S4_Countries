package com.example.countries.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observeNetworkStatus(): Flow<Status>

    enum class Status{
        Unavailable, Available, Losing, Lost
    }
}
