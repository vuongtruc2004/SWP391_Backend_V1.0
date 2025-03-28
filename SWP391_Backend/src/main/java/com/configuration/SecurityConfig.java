package com.configuration;

import com.configuration.custom.AuthenticationEntryPointCustom;
import com.exception.custom.InvalidTokenException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig {

    public static final String[] PUBLIC_ENDPOINTS = {
            "/ws/**",
            "/api/v1/auth/**",
            "/storage/**",
            "/api/v1/files",
            "/api/v1/experts",
            "/api/v1/experts/course",
            "/api/v1/courses",
            "/api/v1/courses/accepted/{courseId}",
            "/api/v1/courses/purchased",
            "/api/v1/courses/suggestion",
            "/api/v1/courses/{courseId}",
            "/api/v1/otp",
            "/api/v1/users/active/{code}",
            "/api/v1/users/change_password",
            "/api/v1/users/request_change_password",
            "/api/v1/users/request_register",
            "/api/v1/hashtags/all",
            "/api/v1/coupons/available",
            "/api/v1/subjects",
            "/api/v1/subjects/courses",
            "/api/v1/subjects/all-inpagination",
            "/api/v1/files/**",
            "/api/v1/blogs",
            "/api/v1/blogs/all",
            "/api/v1/blogs/author",
            "/api/v1/blogs/pinned",
            "/api/v1/blogs/{id}",
            "/api/v1/rates",
            "/api/v1/rates/levels",
            "/api/v1/comments",
            "/api/v1/comments/child-comment/{parentCommentId}",
            "/api/v1/comments/get-comment/{commentId}",
            "/api/v1/campaigns/all",
            "/api/v1/campaigns/price-range",
            "/api/v1/purchase/vnpay-ipn"
    };

    private final SecurityUtil securityUtil;

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