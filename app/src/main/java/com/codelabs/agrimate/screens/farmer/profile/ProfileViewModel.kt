package com.codelabs.agrimate.screens.farmer.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.core.domain.usecase.AuthUseCase
import com.codelabs.core.domain.usecase.DataStoreUseCase
import com.codelabs.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase,
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _isLogout: MutableStateFlow<Resource<Boolean>> =
        MutableStateFlow(Resource.Success(false))
    val isLogout: StateFlow<Resource<Boolean>> = _isLogout.asStateFlow()

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            _toastMessage.emit(message)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authUseCase.logout().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        sendMessage(resource.message.toString())
                        _isLogout.value = Resource.Error(resource.message.toString())
                    }

                    is Resource.Loading -> {
                        _isLogout.value = Resource.Loading()
                    }

                    is Resource.Success -> {
                        sendMessage(resource.data?.message.toString())
                        _isLogout.value = Resource.Success(true)
                        dataStoreUseCase.clearTokens()
                    }
                }
            }
        }
    }
}