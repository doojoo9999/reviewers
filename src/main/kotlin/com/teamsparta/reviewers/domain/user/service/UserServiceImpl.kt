package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.post.dto.response.SignOutResponse
import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignUpRequest
import com.teamsparta.reviewers.domain.user.dto.request.UserUpdateRequest
import com.teamsparta.reviewers.domain.user.dto.response.SignInResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import com.teamsparta.reviewers.domain.user.dto.response.UserUpdateResponse
import com.teamsparta.reviewers.domain.user.dto.response.WithdrawResponse
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
    private val jwtTokenProvider: JwtTokenProvider,
    private val token: JwtTokenProvider
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
    override fun signIn(request: SignInRequest): SignInResponse {
        val user = userRepository.findByEmail(request.email)
            ?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")

        val token = jwtTokenProvider.createToken(user.email)

        // 토큰 생성 후 바로 유효성 검사
        try {
            val validatedSubject = jwtTokenProvider.validateToken(token)
            println("토큰 검증 성공: Subject - $validatedSubject")
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("로그인 실패: 토큰이 유효하지 않습니다.", e)
        }

        // 로그 추가
        println("로그인 성공: ${user.email}")

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
            // 현재 사용자 정보를 기반으로 토큰 생성
            val currentToken = jwtTokenProvider.createToken(user.email)

            // 기존 토큰을 검증
            try {
                val validatedSubject = jwtTokenProvider.validateToken(currentToken)
                if (validatedSubject != user.email) {
                    // 토큰의 subject(사용자 이메일)이 일치하지 않으면 글 수정 불가능
                    throw IllegalArgumentException("글 수정 권한이 없습니다. 다시 로그인 해주세요.")
                }
            } catch (e: IllegalArgumentException) {
                // 토큰 검증 실패 시 글 수정 불가능
                throw IllegalArgumentException("글 수정 권한이 없습니다. 다시 로그인 해주세요.")
            }

            // 사용자 정보 저장
            val updatedUser = userRepository.save(user)

            return UserUpdateResponse(updatedUser.userName, updatedUser.birth, updatedUser.profile_Image, updatedUser.userRole, currentToken)
        } else {
            throw IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.")
        }
    }

    // 로그아웃은 클라이언트에서 JavaScript를 통해 진행됨 (document.cookie = "token=; ) 등등등,.,,,.,.,.,.,. 이라고 함
    override fun signOut(email: String): SignOutResponse {
        return SignOutResponse(message = "complete logout:$email", success = true)
    }

    override fun withdraw(email: String): WithdrawResponse {
        val user = userRepository.findByEmail(email) ?: return WithdrawResponse(message = "not founded email.", success = false)
        userRepository.delete(user)
        return WithdrawResponse(message="complete withdraw email:$email", success = true)
    }
}
