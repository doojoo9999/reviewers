package com.teamsparta.reviewers.domain.user.security

import io.jsonwebtoken.Jwts
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@PropertySource("classpath:jwt.yml")
@Service
class JwtTokenProvider {

    // 토큰 생성
    fun createToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuer("reviewers")
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.SECONDS)))
            .compact()
    }

    // JWT 유효성 검사
    fun validateToken(token: String?): String? {
        return try {
            val claims = Jwts.parserBuilder().build().parseClaimsJwt(token).body
            val expiration = claims.expiration

            if (expiration != null && expiration.before(Date())) {
                throw IllegalArgumentException("토큰이 만료되었습니다.")
            }

            claims.subject
        } catch (e: Exception) {
            null
        }
    }
}