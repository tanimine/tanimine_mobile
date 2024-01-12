package com.codelabs.core.mapper

import com.codelabs.core.data.source.remote.response.auth.AuthMeResponse
import com.codelabs.core.domain.model.AuthMeModel
import com.codelabs.core.utils.Mapper
import javax.inject.Inject

class AuthMeMapper @Inject constructor() : Mapper<AuthMeResponse, AuthMeModel> {
    override fun mapFromResponseToModel(type: AuthMeResponse): AuthMeModel =
        AuthMeModel(
            id = type.id,
            email = type.email,
            role = type.role,
            name = type.userDetail.name,
        )

}