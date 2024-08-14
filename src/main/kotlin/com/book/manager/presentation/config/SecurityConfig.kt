package com.book.manager.presentation.config

import com.book.manager.application.service.AuthenticationService
import com.book.manager.application.service.security.BookManagerUserDetailsService
import com.book.manager.domain.enums.RoleType
import com.book.manager.presentation.handler.BookManagerAccessDeniedHandler
import com.book.manager.presentation.handler.BookManagerAuthenticationEntryPoint
import com.book.manager.presentation.handler.BookManagerAuthenticationFailureHandler
import com.book.manager.presentation.handler.BookManagerAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(private val authenticationService: AuthenticationService) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/admin/**").hasAuthority(RoleType.ADMIN.toString())
            .anyRequest().authenticated()
            .and()
            .csrf().disable() // Disabling CSRF protection
            .formLogin()
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("pass")
            .successHandler(BookManagerAuthenticationSuccessHandler())
            .failureHandler(BookManagerAuthenticationFailureHandler())
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(BookManagerAuthenticationEntryPoint())
            .accessDeniedHandler(BookManagerAccessDeniedHandler())
            .and()
            .cors().configurationSource(corsConfigurationSource())

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return BookManagerUserDetailsService(authenticationService)
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration().apply {
            addAllowedMethod(CorsConfiguration.ALL)
            addAllowedHeader(CorsConfiguration.ALL)
            addAllowedOrigin("http://localhost:8081")
            allowCredentials = true
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }
}
