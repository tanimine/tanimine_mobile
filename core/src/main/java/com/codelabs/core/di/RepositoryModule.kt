package com.codelabs.core.di

import com.codelabs.core.data.impl.AuthRepositoryImpl
import com.codelabs.core.data.impl.DataStoreRepositoryImpl
import com.codelabs.core.data.impl.RegionRepositoryImpl
import com.codelabs.core.domain.repository.AuthRepository
import com.codelabs.core.domain.repository.DataStoreRepository
import com.codelabs.core.domain.repository.RegionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideRegionRepository(regionRepository: RegionRepositoryImpl): RegionRepository

    @Binds
    abstract fun provideDataStoreRepository(dataStoreRepository: DataStoreRepositoryImpl): DataStoreRepository
}