package com.codelabs.core.data.impl

import com.codelabs.core.data.source.remote.AuthRemoteDataSource
import com.codelabs.core.data.source.remote.body.ForgotPasswordBody
import com.codelabs.core.data.source.remote.body.SignInBody
import com.codelabs.core.data.source.remote.body.SignUpBody
import com.codelabs.core.domain.model.AuthMeModel
import com.codelabs.core.domain.model.ForgotPasswordModel
import com.codelabs.core.domain.model.LogoutModel
import com.codelabs.core.domain.model.SignInModel
import com.codelabs.core.domain.model.SignUpModel
import com.codelabs.core.domain.repository.AuthRepository
import com.codelabs.core.mapper.AuthMeMapper
import com.codelabs.core.mapper.ForgotPasswordMapper
import com.codelabs.core.mapper.SignInMapper
import com.codelabs.core.mapper.SignUpMapper
import com.codelabs.core.utils.ErrorUtils
import com.codelabs.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val signUpMapper: SignUpMapper,
    private val signInMapper: SignInMapper,
    private val forgotPasswordMapper: ForgotPasswordMapper,
    private val authMeMapper: AuthMeMapper
) :
    AuthRepository {
    override suspend fun signUp(body: SignUpBody): Flow<Resource<SignUpModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = authRemoteDataSource.signUp(body)
            val model = signUpMapper.mapFromResponseToModel(response)
            emit(Resource.Success(model))
        } catch (e: HttpException) {
            try {
                val responseMessage = ErrorUtils.createErrorResponse(e)
                val model = SignUpModel(responseMessage.code, responseMessage.message)
                emit(Resource.Error(message = e.message().toString(), data = model))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun signIn(body: SignInBody): Flow<Resource<SignInModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = authRemoteDataSource.signIn(body)
            val model = signInMapper.mapFromResponseToModel(response)
            emit(Resource.Success(model))
        } catch (e: HttpException) {
            try {
                val responseMessage = ErrorUtils.createErrorResponse(e)
                val model = SignInModel(responseMessage.code, responseMessage.message)
                emit(Resource.Error(message = e.message().toString(), data = model))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun forgotPassword(body: ForgotPasswordBody): Flow<Resource<ForgotPasswordModel>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = authRemoteDataSource.forgotPassword(body)
                val model = forgotPasswordMapper.mapFromResponseToModel(response)
                emit(Resource.Success(model))
            } catch (e: HttpException) {
                try {
                    val responseMessage = ErrorUtils.createErrorResponse(e)
                    val model =
                        ForgotPasswordModel(code = responseMessage.code, responseMessage.message)
                    emit(Resource.Error(message = e.message().toString(), data = model))
                } catch (e: Exception) {
                    emit(Resource.Error(e.message.toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun authMe(): Flow<Resource<AuthMeModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = authRemoteDataSource.authMe()
            val model = authMeMapper.mapFromResponseToModel(response.data)
            emit(Resource.Success(model))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun logout(): Flow<Resource<LogoutModel>> = flow {
        emit(Resource.Loading())
        try {
            val response = authRemoteDataSource.logout()
            emit(Resource.Success(LogoutModel(response.code, "Berhasil Logout")))
        } catch (e: HttpException) {
            if (e.code() >= 400) {
                emit(Resource.Success(LogoutModel(e.code(), e.message())))
            } else {
                emit(Resource.Error(message = e.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}