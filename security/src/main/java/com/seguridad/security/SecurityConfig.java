package com.seguridad.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Habilitamos Spring Security
public class SecurityConfig {

    // Protegemos la API REST mediante Roles.

    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http ) throws  Exception{
        http.authorizeHttpRequests(request ->
                request.requestMatchers("/register/**")
                .permitAll()
                        .requestMatchers("/Vip/**").hasRole("VIP_USER")
                        .requestMatchers("/Admin/**").hasRole("ADMIN_USER")

        )
                // Desactivamos el CSRF Ya que no usaremos Navegador

                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
        ;
        return http.build();

    }

}
