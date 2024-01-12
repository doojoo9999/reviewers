package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.user.common.UserRole
import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignUpRequest
import com.teamsparta.reviewers.domain.user.dto.response.SignInResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import com.teamsparta.reviewers.domain.user.model.toSignUpResponse
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) : UserService {

    @Transactional
    override fun signUp(
        email: String,
        request: SignUpRequest
    ): SignUpResponse {
        // email 중복검사
        userRepository.findByEmail(email)?.let { throw IllegalStateException("Email is already in use") }

        return userRepository.save(
            UserEntity(
                password = encoder.encode(request.password),
                email = request.email,
                birth = request.birth,
                userName = request.userName,
                userRole = request.userRole
            )
        ).toSignUpResponse()
    }

    @Transactional
    override fun signIn(
        userName:String,
        request: SignInRequest
    ): SignInResponse {
        val user = userRepository.findByEmail(request.email)
        if (user != null) {
            encoder.matches(request.password, user.password)

            return SignInResponse(userName = user.userName, UserRole.USER)
        } else {
            throw IllegalStateException("Email is already in use")
        }
    }


}