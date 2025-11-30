package com.presencify_admin.feature_admin_auth.domain.use_case

import com.presencify_admin.feature_admin_auth.domain.repository.TokenRepository
import javax.inject.Inject

class SaveRefreshTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(adminRefreshToken: String) {
        tokenRepository.saveAdminRefreshToken(adminRefreshToken)
    }
}