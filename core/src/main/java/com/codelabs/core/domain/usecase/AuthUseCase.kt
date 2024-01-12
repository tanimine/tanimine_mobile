package com.codelabs.core.domain.usecase

import com.codelabs.core.data.source.remote.body.ForgotPasswordBody
import com.codelabs.core.data.source.remote.body.SignInBody
import com.codelabs.core.data.source.remote.body.SignUpBody
import com.codelabs.core.domain.model.AuthMeModel
import com.codelabs.core.domain.model.ForgotPasswordModel
import com.codelabs.core.domain.model.LogoutModel
import com.codelabs.core.domain.model.SignInModel
import com.codelabs.core.domain.model.SignUpModel
import com.codelabs.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    suspend fun signUp(body: SignUpBody): Flow<Resource<SignUpModel>>
    suspend fun signIn(body: SignInBody): Flow<Resource<SignInModel>>
    suspend fun forgotPassword(body: ForgotPasswordBody): Flow<Resource<ForgotPasswordModel>>
    suspend fun authMe(): Flow<Resource<AuthMeModel>>
    suspend fun logout(): Flow<Resource<LogoutModel>>
}