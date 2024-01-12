package com.teamsparta.reviewers.domain.user.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.SecretKey
import java.util.Base64
import javax.crypto.spec.SecretKeySpec


@PropertySource("classpath:jwt.yml")
@Service
class JwtTokenProvider {

    // 토큰 생성
    fun createToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuer("issuer")
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(24, ChronoUnit.HOURS))) // 예시로 1시간 유효한 토큰
            .compact()
    }

    // JWT 유효성 검사
    fun validateToken(token: String?): String? {
        return try {
            val claims = Jwts.parserBuilder().build().parseClaimsJwt(token).body
            claims.subject
        } catch (e: Exception) {
            null
        }
    }
}