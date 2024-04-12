package com.example.ipapp.presentation

import com.example.ipapp.domain.model.IpAddress


data class IpListState(
    val ipAddresses: List<IpAddress> = emptyList()
)
