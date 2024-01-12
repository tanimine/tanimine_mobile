package com.codelabs.agrimate.di

import com.codelabs.core.domain.usecase.AuthInteractor
import com.codelabs.core.domain.usecase.AuthUseCase
import com.codelabs.core.domain.usecase.DataStoreInteractor
import com.codelabs.core.domain.usecase.DataStoreUseCase
import com.codelabs.core.domain.usecase.RegionInteractor
import com.codelabs.core.domain.usecase.RegionUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAuthUseCase(authInteractor: AuthInteractor): AuthUseCase

    @Binds
    @Singleton
    abstract fun provideRegionUseCase(regionInteractor: RegionInteractor): RegionUseCase

    @Binds
    @Singleton
    abstract fun provideDataStoreUseCase(dataStoreInteractor: DataStoreInteractor): DataStoreUseCase
}