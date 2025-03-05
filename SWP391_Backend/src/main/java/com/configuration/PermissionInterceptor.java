

package com.configuration;
import com.entity.PermissionEntity;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.exception.custom.UserException;
import com.service.UserService;
import com.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import java.util.List;
public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    @Transactional
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler)
            throws UserException {
        String path = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();
        System.out.println(">>> RUN preHandle");
        System.out.println(">>> path= " + path);
        System.out.println(">>> httpMethod= " + httpMethod);
        System.out.println(">>> requestURI= " + requestURI);

        String email= JwtService.extractUsernameFromToken().isPresent() ==true ? JwtService.extractUsernameFromToken().get() : "";
        if(email!=null){
            UserEntity user=this.userService.getUserByEmail(email);
            if(user!=null){
                RoleEntity role= user.getRole();
                if(role!=null){
                    List<PermissionEntity> permissions=role.getPermissions();
                    Boolean isAllow=permissions.stream().anyMatch(item->item.getApiPath().equals(path) && item.getApiMethod().equals(httpMethod));
                    System.out.println(">>> isAllow :" +isAllow );
                    if(!isAllow){
                        throw new UserException("Access denied");
                    }
                }else{
                    throw new UserException("Access denied");
                }
            }
        }
        return true;
    }

}