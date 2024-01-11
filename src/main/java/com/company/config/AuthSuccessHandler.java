package com.company.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
/*
 * 🖍️...
 * · Authentication: It’s about validating the received credentials, where we verify that both username and password match the ones that our application recognizes.
 * · Authorization: It’s about verifying if the successfully authenticated user has permissions to access certain functionality of the application.
 *
 * · AuthenticationSuccessHandler interface: Redirection to different pages based on the roles.
 * · Authentication interface: Verifies that a user is allowed access to the system.
 *   It also maintains the authentication information, typically passwords, which are used to authorize user access.
 * · AuthorityUtils class: Utility method for manipulating GrantedAuthority collections etc.
 */
@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities()); // Brings all the roles
        if(roles.contains("Admin")){
            response.sendRedirect("/user/create");
        }
        if(roles.contains("Manager")){
            response.sendRedirect("/project/create");
        }
        if(roles.contains("Employee")){
            response.sendRedirect("/task/employee");
        }
    }



}
