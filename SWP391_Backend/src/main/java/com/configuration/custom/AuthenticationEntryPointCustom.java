package com.configuration.custom;

import com.dto.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.BuildResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class AuthenticationEntryPointCustom implements AuthenticationEntryPoint {

    private final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();

    private final ObjectMapper mapper;

    public AuthenticationEntryPointCustom(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        this.delegate.commence(request, response, authException);
        response.setContentType("application/json;charset=UTF-8");
        String errorMessage = Optional.ofNullable(authException.getCause())
                .map(Throwable::getMessage)
                .orElse(authException.getMessage());

        ApiResponse<Void> apiResponse = BuildResponse.buildApiResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "JWT Token không hợp lệ!",
                errorMessage,
                null
        );
        mapper.writeValue(response.getWriter(), apiResponse);
    }
}