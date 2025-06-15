package com.attendify_admin.feature_admin_auth.domain.use_case

import com.attendify_admin.feature_admin_auth.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAccessTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(): Flow<String?> = tokenRepository.readAdminAccessToken()
}