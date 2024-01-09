package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.exception.SameAccountException
import com.teamsparta.reviewers.domain.user.dto.request.CreateUserRequest
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import com.teamsparta.reviewers.domain.user.model.toResponse
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
) : UserService {

    override fun signUp (createUserRequest : CreateUserRequest) : UserResponse {
        // Email 중복 검사
        var user : UserEntity? = userRepository.findByEmail(createUserRequest.email)
        if (user != null) {
            throw SameAccountException(createUserRequest.email)
        }

        user = UserEntity(
            createUserRequest.email,
            createUserRequest.password,
            createUserRequest.username,
            createUserRequest.birth
        )

        return userRepository.save(user).toResponse()
    }


}