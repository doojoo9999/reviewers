package com.teamsparta.reviewers.domain.user.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

@Service
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:application.yml")
class JwtTokenProvider {

    lateinit var secretKey: String
    var expirationSeconds: Long = 0
    lateinit var issuer: String

    // JwtTokenProvider 클래스의 토큰 생성 메서드 수정
    fun createToken(email: String): String {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())
        return Jwts.builder()
            .setSubject(email)
            .setIssuer(issuer)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(expirationSeconds, ChronoUnit.SECONDS)))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String?): String {
        try {
            val key = Keys.hmacShaKeyFor(secretKey.toByteArray())
            val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
            val expiration = claims.expiration

            if (expiration != null) {
                val currentDateTime = LocalDateTime.now()
                val expirationDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

                if (expirationDateTime.isBefore(currentDateTime)) {
                    throw IllegalArgumentException("토큰이 만료되었습니다.")
                }
            }

            return claims.subject
        } catch (e: Exception) {
            throw IllegalArgumentException("토큰 유효성 검사 실패", e)
        }
    }
}