package com.teamsparta.reviewers.domain.user.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*


@Service
@PropertySource("classpath:jwt.yml")
class JwtTokenProvider(
    @Value("\${secret-key}") private val secretKey: String,
    @Value("\${expiration-minutes}") private val expirationMinutes: Long,
    @Value("\${issuer}") private val issuer: String
) {

    init {
        println("secretKey: $secretKey, expirationMinutes: $expirationMinutes, issuer: $issuer")
    }

    // 토큰 생성
    fun createToken(email: String): String {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        val issuedAt = Date.from(Instant.now())
        val expiration = Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES))

        return Jwts.builder()
            .setSubject(email)
            .setIssuer(issuer)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
            .also {
                println("Generated Token: $it") // 디버깅을 위해 생성된 토큰을 출력
            }
    }

    // JWT 유효성 검사
    fun validateToken(token: String?): String? {
        return try {
            val claims = Jwts.parserBuilder().build().parseClaimsJwt(token).body
            val expiration = claims.expiration

            if (expiration != null) {
                val currentDateTime = LocalDateTime.now()
                val expirationDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

                if (expirationDateTime.isBefore(currentDateTime)) {
                    throw IllegalArgumentException("토큰이 만료되었습니다.")
                }
            }

            claims.subject
        } catch (e: Exception) {
            null
        }
    }
}