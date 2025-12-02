package com.example.dev_app_mobile.domain.usecase.auth

import com.example.dev_app_mobile.domain.repository.UserRepository
import com.example.dev_app_mobile.util.Result
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        return when (val result = userRepository.login(email, password)) {
            is Result.Success -> Result.Success(true)
            is Result.Error -> Result.Error(result.message)
            is Result.Loading -> Result.Loading
        }
    }
}