package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.exception.SameAccountException
import com.teamsparta.reviewers.domain.user.dto.request.CreateUserRequest
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse
import com.teamsparta.reviewers.domain.user.model.*
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
) : UserService {

    @Transactional
    override fun signUp(
        createUserRequest: CreateUserRequest
    ): UserResponse {
        // email 중복검사
        val sameAccountCheckUser: UserEntity? = userRepository.findByEmail(createUserRequest.email)

        if (sameAccountCheckUser != null) {
            // 이메일이 이미 존재하는 경우
            throw SameAccountException(createUserRequest.email)
        }

        val signUpUser = UserEntity(
            createUserRequest.email,
            createUserRequest.password,
            createUserRequest.username,
            createUserRequest.birth,
            createUserRequest.userRole,
        )

        val savedUser = userRepository.save(signUpUser)

        return savedUser.toResponse()
    }
}