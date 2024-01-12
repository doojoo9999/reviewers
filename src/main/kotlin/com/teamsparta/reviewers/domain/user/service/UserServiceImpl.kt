package com.teamsparta.reviewers.domain.user.service

import com.teamsparta.reviewers.domain.exception.SameAccountException
import com.teamsparta.reviewers.domain.user.dto.request.CreateUserRequest
import com.teamsparta.reviewers.domain.user.dto.request.LoginRequest
import com.teamsparta.reviewers.domain.user.dto.response.UserResponse
import com.teamsparta.reviewers.domain.user.model.*
import com.teamsparta.reviewers.domain.user.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.transaction.Transactional
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.naming.AuthenticationException


@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
) : UserService {

    private val passwordEncoder = BCryptPasswordEncoder()
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

        val encodedPassword = passwordEncoder.encode(createUserRequest.password)

        val signUpUser = UserEntity(
            createUserRequest.email,
            encodedPassword,
            createUserRequest.username,
            createUserRequest.birth,
            createUserRequest.userRole,
        )

        val savedUser = userRepository.save(signUpUser)

        return savedUser.toResponse()
    }

    override fun login(loginRequest: LoginRequest): UserResponse {
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
            loginRequest.email,
            loginRequest.password
        )

        try {
            val authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken)
            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: AuthenticationException) {
            log.error("Authentication failed", e)
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed", e)
        }

        val userEntity: UserEntity = userRepository.findByEmail(loginRequest.email)
            ?: throw UsernameNotFoundException("User not found with email : ${loginRequest.email}")

        val token: String = Jwts.builder()
            .setSubject(userEntity.userId.toString())
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(SignatureAlgorithm.HS512, "1q2w3e4r")
            .compact()

        log.info("Generated token: $token") // 토큰이 제대로 생성되었는지 ??

        return UserResponse(
            email = userEntity.email,
            username = userEntity.username,
            birth = userEntity.birth,
            password = "",
            userRole = userEntity.userRole,
            token = token
        )
    }
}