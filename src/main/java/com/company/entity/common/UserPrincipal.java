package com.company.entity.common;

import com.company.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/*
 * 🦋 Spring Security
 * ■ UserDetailsService interface: It is responsible for retrieving user info from a database and returning an instance of the UserDetails interface.
 *   It has a single method called loadUserByUsername(), which takes a username as a parameter and returns a UserDetails object.
 * ■ UserDetails interface: It represents the authenticated user in the Spring Security framework and contains details such as
 *   the user’s username, password, authorities (roles), and additional attributes.
 * ■ User class: It implements UserDetails interface and is used to represent user account details, such as the username, password,
 *   and authorities (roles) associated with a user. The User class provides a fluent API for constructing UserDetails objects
 *   with various attributes. It offers methods to set the username, password, and roles for a user, among other properties.
 * 🖍️...
 * · We created a UserPrincipal class to map the Entity User to the Spring User. For that reason, the UserPrincipal class
 *   implements the UserDetails interface, and we have to override all the methods that come from the UserDetails interface.
 * · Spring User class implements from UserDetails interface.
 * · GrantedAuthority interface: Represents an authority, a privilege granted to an authenticated user.
 *
 */
public class UserPrincipal implements UserDetails {
    private User user; // HAS A relationship: To access User entity properties. It is not an injection.
    public UserPrincipal(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(this.user.getRole().getDescription());
        authorityList.add(authority);
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.user.getPassWord();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isEnabled();
    }

    public Long getId(){
        return this.user.getId();
    }




}
