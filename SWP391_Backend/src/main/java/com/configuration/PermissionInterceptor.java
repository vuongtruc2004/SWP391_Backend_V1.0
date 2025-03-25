package com.configuration;

import com.entity.PermissionEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.UserException;
import com.helper.UserServiceHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;
import java.util.Objects;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String httpMethod = request.getMethod();
        System.out.println(">>> RUN preHandle");
        System.out.println(">>> path= " + path);
        System.out.println(">>> httpMethod= " + httpMethod);
        UserEntity user = userServiceHelper.extractUserFromToken();
        if (user == null) {
            throw new UserException("Bạn cần đăng nhập để thực hiện chức năng này!");
        }
        RoleEntity role = user.getRole();
        if (role == null) {
            throw new UserException("Bạn cần đăng nhập để thực hiện chức năng này!");
        }
        List<PermissionEntity> permissions = role.getPermissions();
        boolean isAllowed = permissions.stream()
                .anyMatch(permission -> Objects.equals(permission.getApiPath(), path)
                        && permission.getApiMethod().name().equalsIgnoreCase(httpMethod));

        System.out.println(">>> isAllowed = " + isAllowed);
        if (!isAllowed) {
            throw new UserException("Bạn không có quyền truy cập tài nguyên này!");
        }

        return true;
    }
}
