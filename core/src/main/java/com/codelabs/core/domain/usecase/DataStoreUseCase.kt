package com.codelabs.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface DataStoreUseCase {
    fun getAccessToken(): Flow<String?>
    fun getRefreshToken(): Flow<String?>
    suspend fun saveAccessToken(accessToken: String)
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun deleteAccessToken()
    suspend fun deleteRefreshToken()
    suspend fun clearTokens()
}