package com.configuration;


import com.entity.PermissionEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.NotFoundException;
import com.repository.UserRepository;
import com.service.UserService;
import com.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws NotFoundException {
        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();
        System.out.println(">>> RUN preHandle");
        System.out.println(">>> path= " + path);
        System.out.println(">>> httpMethod= " + httpMethod);
        System.out.println(">>> requestURI= " + requestURI);
        Optional<String> email = JwtService.extractUsernameFromToken();
        if (email.isPresent()) {
            UserEntity user = this.userRepository.findByEmail(email.get());
            if (user != null) {
                RoleEntity role = user.getRole();
                if (role != null) {
                    List<PermissionEntity> permissions = role.getPermissions();
                    Boolean isAllow = permissions.stream().anyMatch(item -> (item.getApiPath().equals(path) && item.getApiMethod().name().equals(httpMethod)));
                    System.out.println(">>> isAllow :" + isAllow);
                    if (!isAllow) {
                        throw new NotFoundException("Bạn không có quyền truy cập tài nguyên này!");
                    }
                } else {
                    throw new NotFoundException("Bạn cần đăng nhập để thực hiện chức năng này!");
                }
            }
        }
        return true;
    }
}