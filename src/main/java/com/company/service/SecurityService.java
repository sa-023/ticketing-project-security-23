package com.company.service;
import org.springframework.security.core.userdetails.UserDetailsService;
/*
 * 🖍️...
 * · Spring security work with UserDetailsService interface for authentication and authorize features.
 * · To authenticate the UI, we should use the UserDetailsService that Sprint provides. Because of that, our SecurityService
 *   extends from the UserDetailsService.
 */
public interface SecurityService extends UserDetailsService {

}
