package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.user.common.UserRole
import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignUpRequest
import com.teamsparta.reviewers.domain.user.dto.request.UserUpdateRequest
import com.teamsparta.reviewers.domain.user.dto.response.*
import com.teamsparta.reviewers.domain.user.model.UserEntity
import com.teamsparta.reviewers.domain.user.model.toSignUpResponse
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import com.teamsparta.reviewers.domain.user.security.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
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
                userRole = request.userRole,
                profile_Image = request.profile_Image
            )
        ).toSignUpResponse()
    }

    @Transactional
    override fun signIn(
        request: SignInRequest
    ): SignInResponse {
        val user = userRepository.findByEmail(request.email)
            ?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")

        val token = jwtTokenProvider.createToken(user.email)
//        if (jwtTokenProvider.validateToken(token) == null) {
//            throw IllegalArgumentException("토큰이 유효하지 않습니다.")
//        }

        jwtTokenProvider.validateToken(token)

        return SignInResponse(user.email, user.userName, user.userRole, token)
    }

    @Transactional
    override fun userUpdate(
        email: String,
        request: UserUpdateRequest
    ): UserUpdateResponse {
        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("해당 이메일의 사용자를 찾을 수 없습니다.")

        if (encoder.matches(request.password, user.password)) {
            user.userName = request.userName
            user.birth = request.birth
            user.profile_Image = request.profile_Image
            user.userRole = request.userRole

            if (request.newPassword.isNotBlank()) {
                val newPasswordEncoded = encoder.encode(request.newPassword)
                user.password = newPasswordEncoded
            }

            userRepository.save(user)

            return UserUpdateResponse(user.userName, user.birth, user.profile_Image, user.userRole)
        } else {
            throw IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.")
        }
    }
    override fun signOut(email: String): SignOutResponse {
        return SignOutResponse(message = "complete logout:$email", success = true)
    }



    override fun withdraw(email: String): WithdrawResponse {
        val user = userRepository.findByEmail(email) ?: return WithdrawResponse(message = "not founded email.", success = false)
        userRepository.delete(user)
        return WithdrawResponse(message="complete withdraw email:$email", success = true)
    }

}