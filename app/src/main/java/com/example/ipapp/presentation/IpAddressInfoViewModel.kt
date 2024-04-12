package com.example.ipapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipapp.common.Resource
import com.example.ipapp.data.local.IpAddressDatabase
import com.example.ipapp.data.remote.mapper.toIpAddressEntity
import com.example.ipapp.data.remote.repository.IpAddressRepository
import com.example.ipapp.domain.model.IpAddress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class IpAddressInfoViewModel @Inject constructor(
    private val repository: IpAddressRepository,
    private val db: IpAddressDatabase,
) : ViewModel() {

    private val _state = mutableStateOf(IpAddressState())
    val state: State<IpAddressState> = _state

    var ipState by mutableStateOf(IpListState())

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getLocalIpAddresses()
    }

    fun onSearch(query: String) {
        viewModelScope.launch {
            repository.getIpAddress(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                ipAddress = result.data,
                                isLoading = false
                            )
                            state.value.ipAddress?.let {
                                db.dao.insertIpAddress(it.toIpAddressEntity())

                                getLocalIpAddresses()
                            }
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                ipAddress = result.data,
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                ipAddress = result.data,
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    private fun getLocalIpAddresses() {
        viewModelScope.launch(Dispatchers.IO){
            repository
                .getAllIpAddresses()
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { ipList ->
                                ipState = ipState.copy(
                                    ipAddresses = ipList
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        else -> {}
                    }
                }
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}