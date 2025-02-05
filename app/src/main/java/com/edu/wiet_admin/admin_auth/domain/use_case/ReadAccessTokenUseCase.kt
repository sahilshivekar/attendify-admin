package com.edu.wiet_admin.admin_auth.domain.use_case

import com.edu.wiet_admin.admin_auth.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAccessTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    operator fun invoke(): Flow<String?> = tokenRepository.readAdminAccessToken()
}