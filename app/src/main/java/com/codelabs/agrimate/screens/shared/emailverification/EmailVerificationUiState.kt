package com.codelabs.agrimate.screens.shared.emailverification

data class EmailVerificationUiState(
    val code: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)