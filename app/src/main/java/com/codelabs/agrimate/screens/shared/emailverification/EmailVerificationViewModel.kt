package com.codelabs.agrimate.screens.shared.emailverification

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// TODO: create verification repository
// TODO: connect to viewmodel
// TODO: open browser after code verification is valid

@HiltViewModel
class EmailVerificationViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(EmailVerificationUiState())
    val uiState: StateFlow<EmailVerificationUiState> = _uiState.asStateFlow()

    fun setCode(value: String) {
        _uiState.update {
            it.copy(code = value)
        }
    }

    private fun setIsLoading(state: Boolean) {
        _uiState.update {
            it.copy(isLoading = state)
        }
    }

    private fun setIsSuccess(state: Boolean) {
        _uiState.update {
            it.copy(isSuccess = state)
        }
    }
}