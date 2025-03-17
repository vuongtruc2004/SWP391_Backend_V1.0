package com.configuration;


import com.exception.custom.NotFoundException;
import com.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
public class PermissionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
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
        return true;
//        String email= SecurityUtil.getCurrentUserLogin().isPresent() ==true ? SecurityUtil.getCurrentUserLogin().get() : "";
//        if(email!=null){
//            User user=this.userService.getUserByUsername(email);
//            if(user!=null){
//                Role role= user.getRole();
//                if(role.getRoleName().equalsIgnoreCase("ADMIN")){
//                    return true;
//                }
//                if(role!=null){
//                    List<Permission> permissions=role.getPermissions();
//                    Boolean isAllow=permissions.stream().anyMatch(item->item.getApiPath().equals(path) && item.getApiMethod().equals(httpMethod));
//                    System.out.println(">>> isAllow :" +isAllow );
//                    if(!isAllow){
//                        throw new UserNotFoundException("Access denied");
//                    }
//                }else{
//                    throw new UserNotFoundException("Access denied");
//                }
//            }
//        }
//        return true;
    }

}