package com.example.minipost.http.config;

import com.example.minipost.http.filter.JwtValidateFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class MinipostSecurityConfig {

    @Bean
    SecurityFilterChain securityConfig(HttpSecurity httpSecurity) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                    config.setAllowedMethods(Collections.singletonList("*"));
//                    config.setAllowCredentials(true);
//                    config.setAllowedHeaders(Collections.singletonList("*"));
//                    config.setExposedHeaders(Collections.singletonList("Authorization"));
//                    config.setMaxAge(3600L);
//                    return config;
//                }))
//                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler)
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .cors().disable()
                .csrf().disable()
                .addFilterBefore(new JwtValidateFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/signup").hasRole("ADMIN")
//                        .requestMatchers("/signin").permitAll()
                        .requestMatchers("/signin", "/signup").permitAll()
                        .anyRequest().authenticated());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
