package com.benjaminstammen.bfi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {

    // Going to use a FE server, so should be OK to disable CSRF.
    // Really only using it for Swagger, though.
    // Possible workaround: https://github.com/springfox/springfox/issues/1450
    // Or maybe I'll just use gRPC or something in the future.
    @Bean
    fun httpSecurity(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf()
                .disable()
            .authorizeRequests()
                .anyRequest()
                    .permitAll()
            .and()
                .cors()
            .and()
                .build()
    }
}
