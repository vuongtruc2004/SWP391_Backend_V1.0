package com.service.auth;

import com.dto.response.LoginResponse;
import com.dto.response.UserResponse;
import com.entity.UserEntity;
import com.exception.custom.InvalidTokenException;
import com.exception.custom.UserException;
import com.repository.UserRepository;
import com.util.BuildResponse;
import com.util.SecurityUtil;
import com.util.enums.AccountTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class JwtService {

    private final SecurityUtil securityUtil;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public JwtService(SecurityUtil securityUtil, JwtEncoder jwtEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.securityUtil = securityUtil;
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public String createJWTToken(String email, String accountType, Long expireTime) {
        Instant now = Instant.now();
        Instant expireAt = now.plusSeconds(expireTime);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(expireAt)
                .subject(email)
                .claim("accountType", accountType)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(securityUtil.JWT_ALGORITHMS).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, jwtClaimsSet)).getTokenValue();

    }

    public LoginResponse letRefreshToken(String refreshToken) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder
                .withSecretKey(securityUtil.getSecretKey())
                .macAlgorithm(securityUtil.JWT_ALGORITHMS)
                .build();

        try {
            Jwt jwt = jwtDecoder.decode(refreshToken);
            String email = jwt.getSubject();
            String accountType = jwt.getClaim("accountType").toString();
            UserEntity userEntity = userRepository.findByEmailAndAccountType(email, AccountTypeEnum.valueOf(accountType))
                    .orElseThrow(() -> new UserException("User not found!"));
            UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);

            String accessToken = createJWTToken(email, accountType, securityUtil.accessTokenExpiration);
            String newRefreshToken = createJWTToken(email, accountType, securityUtil.refreshTokenExpiration);
            Instant now = Instant.now();
            Instant expireAt = now.plusSeconds(securityUtil.accessTokenExpiration);

            userEntity.setRefreshToken(newRefreshToken);
            userRepository.save(userEntity);
            return BuildResponse.buildLoginResponse(userResponse, accessToken, expireAt, refreshToken);

        } catch (Exception e) {
            throw new InvalidTokenException("Refresh token is invalid!");
        }
    }

    public static Optional<String> extractUsernameFromToken() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        } else if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return Optional.ofNullable(userDetails.getUsername());
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return Optional.ofNullable(jwt.getSubject());
        } else if (authentication.getPrincipal() instanceof String username) {
            return Optional.of(username);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String> extractAccountTypeFromToken() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return Optional.ofNullable(jwt.getClaimAsString("accountType"));
        } else {
            return Optional.empty();
        }
    }
}
