package com.teamsparta.reviewers.domain.authorization

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeRequests {
                authorize("/swagger-ui/**", permitAll)
                authorize("/api/member/**", permitAll)
                authorize("/api/member/signup", permitAll)
                authorize("/api/member/signin", permitAll)
//                authorize("/api/**")
            }
        }
        return http.build()
    }
}







//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf {
//                    csrfConfig: CsrfConfigurer<HttpSecurity> -> csrfConfig.disable()
//            } // 1번
//            .headers { headerConfig: HeadersConfigurer<HttpSecurity?> ->
//                headerConfig.frameOptions(
//                    { frameOptionsConfig -> frameOptionsConfig.disable() }
//                )
//            } // 2번
//            .authorizeHttpRequests { authorizeRequests ->
//                authorizeRequests
////                    .requestMatchers(AntPathRequestMatcher("/h2-console/**")).permitAll()
//                    .requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
//                    .requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
//                    .requestMatchers(AntPathRequestMatcher("/api/member/signup")).permitAll()
//                    .requestMatchers(AntPathRequestMatcher("/api/**")).permitAll()
////                    .requestMatchers(AntPathRequestMatcher("/swagger-resources/**")).permitAll()
////                    .requestMatchers(AntPathRequestMatcher("/favicon.ico")).permitAll()
////                    .requestMatchers(AntPathRequestMatcher("/error")).permitAll()
////                    .requestMatchers(AntPathRequestMatcher("/api/v1/client/**")).hasRole(RoleEnum.BASIC_PLAN.name)
////                    .requestMatchers(AntPathRequestMatcher("/api/v1/posts/**")).hasRole(RoleEnum.BASIC_PLAN.name)
////                    .requestMatchers(AntPathRequestMatcher("/api/v1/admins/**")).hasRole(RoleEnum.ADMIN.name)
//                    .anyRequest().authenticated()
//            } // 3번
//        return http.build()