package com.shika.blog.service;

import com.shika.blog.model.User;
import com.shika.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // for Optional<User>
        // User user = userRepository.findByUsername(username).orElseThrow(() ->
                // new UsernameNotFoundException("User " + username + " not found!"));
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER NOT FOUND!");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    true, true, true, true, getAuthorities("ROLE_USER"));
        }
    }


    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singleton(new SimpleGrantedAuthority(role_user));
    }
}
