package com.configuration;

import com.configuration.custom.AuthenticationEntryPointCustom;
import com.exception.custom.InvalidTokenException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.util.SecurityUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {
            "/storage/**",
            "/api/v1/auth/**",
            "api/v1/blogs/**",
            "api/v1/courses/**",
            "api/v1/subjects/**",
            "/api/v1/users/**",
            "/api/v1/otp/**",
            "/api/v1/orders/**",
            "/api/v1/orderDetails/**",
            "/api/v1/rates/**",
            "/api/v1/experts/**",
            "/api/v1/questions/**",
            "/api/v1/notifications/**",
            "/api/v1/quizzes/**",
            "/api/v1/answers/**",
            "/api/v1/files/**",
            "/api/v1/purchase/**",
            "/api/v1/hashtags/**",
            "/api/v1/coupons/**",
    };

    private final SecurityUtil securityUtil;

    public SecurityConfig(SecurityUtil securityUtil) {
        this.securityUtil = securityUtil;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, AuthenticationEntryPointCustom authenticationEntryPointCustom) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                                .requestMatchers("/ws/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(
                        oauth2 -> oauth2
                                .jwt(Customizer.withDefaults())
                                .authenticationEntryPoint(authenticationEntryPointCustom)
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(securityUtil.getSecretKey()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(securityUtil.getSecretKey())
                .macAlgorithm(securityUtil.JWT_ALGORITHMS)
                .build();

        return token -> {
            try {
                return jwtDecoder.decode(token);
            } catch (InvalidTokenException e) {
                throw new InvalidTokenException(e.getMessage());
            }
        };
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}