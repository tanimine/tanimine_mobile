package com.codelabs.core.domain.usecase

import com.codelabs.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreInteractor @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    DataStoreUseCase {
    override fun getAccessToken(): Flow<String?> = dataStoreRepository.getAccessToken()

    override fun getRefreshToken(): Flow<String?> = dataStoreRepository.getRefreshToken()

    override suspend fun saveAccessToken(accessToken: String) =
        dataStoreRepository.saveAccessToken(accessToken)

    override suspend fun saveRefreshToken(refreshToken: String) =
        dataStoreRepository.saveRefreshToken(refreshToken)

    override suspend fun deleteAccessToken() = dataStoreRepository.deleteAccessToken()

    override suspend fun deleteRefreshToken() = dataStoreRepository.deleteRefreshToken()
    override suspend fun clearTokens() = dataStoreRepository.clearTokens()

}