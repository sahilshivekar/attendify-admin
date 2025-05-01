package com.attendify_admin.admin_auth.domain.use_case

import com.attendify_admin.admin_auth.domain.repository.TokenRepository
import javax.inject.Inject

class RemoveAuthTokensUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(){
        tokenRepository.clearAdminTokens()
    }
}