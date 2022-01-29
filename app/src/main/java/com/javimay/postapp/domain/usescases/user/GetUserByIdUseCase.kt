package com.javimay.postsapp.domain.usescases.user

import com.javimay.postsapp.data.model.User
import com.javimay.postsapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend fun execute(userId: Int): User = usersRepository.getUserById(userId)
}