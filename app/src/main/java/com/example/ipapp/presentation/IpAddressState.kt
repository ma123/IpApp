package com.example.ipapp.presentation

import com.example.ipapp.domain.model.IpAddress


data class IpAddressState(
    val ipAddress: IpAddress? = null,
    val isLoading: Boolean = false
)
