package com.teamsparta.reviewers.domain.user.security

import com.teamsparta.reviewers.domain.user.common.UserRole
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig() {

    fun configure(web: WebSecurity) {
        web.ignoring().requestMatchers( PathRequest.toStaticResources().atCommonLocations())
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeRequests {
                authorize("/swagger-ui/**", permitAll)
                authorize("/v3/api-docs/**", permitAll)
                authorize("/api/user/signup", permitAll)
                authorize("/api/user/signin", permitAll)
                authorize("*", permitAll)
                authorize("/admin/**", hasAuthority(UserRole.ADMIN.name))
            }
            formLogin {
                loginPage = "/signin"
            }
        }
        return http.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}