package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.user.common.UserRole
import com.teamsparta.reviewers.domain.user.dto.request.SignInRequest
import com.teamsparta.reviewers.domain.user.dto.request.SignUpRequest
import com.teamsparta.reviewers.domain.user.dto.response.SignInResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignOutResponse
import com.teamsparta.reviewers.domain.user.dto.response.SignUpResponse
import com.teamsparta.reviewers.domain.user.dto.response.WithdrawResponse
import com.teamsparta.reviewers.domain.user.model.UserEntity
import com.teamsparta.reviewers.domain.user.model.toSignUpResponse
import com.teamsparta.reviewers.domain.user.repository.UserRepository
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
        request: SignInRequest
    ): SignInResponse {
        val user = userRepository.findByEmail(request.email)
            ?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")

        return SignInResponse(user.email, user.userName, user.userRole)



//        val member = memberRepository.findByAccount(request.account)
//            ?.takeIf { encoder.matches(request.password, it.password) } ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
//        val token = tokenProvider.createToken("${member.id}:${member.type}")
//        return SignInResponse(member.name, member.type, token)
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

//        val user = userRepository.findByEmail(request.email)
//        if (user != null) {
//            if(encoder.matches(request.password, user.password)) {
//                return SignInResponse(email = user.email, UserRole.USER)
//            }
//        } else {
//            throw IllegalStateException("Email is already in use")
//        }
//        return SignInResponse()
//    }


}