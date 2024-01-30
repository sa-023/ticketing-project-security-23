package com.company.service.impl;
import com.company.entity.User;
import com.company.entity.common.UserPrincipal;
import com.company.repository.UserRepository;
import com.company.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // This method will run automatically by Spring.
        User user = userRepository.findByUserName(username);
        if(user==null){
            throw  new UsernameNotFoundException("This user does not exists");
        }
        return new UserPrincipal(user); // UserPrincipal is mapping the Entity User to the Spring Use.
    }



}