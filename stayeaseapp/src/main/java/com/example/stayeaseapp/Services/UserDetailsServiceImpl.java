package com.example.stayeaseapp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.stayeaseapp.Models.UserDetailsImpl;
import com.example.stayeaseapp.Models.Users;
import com.example.stayeaseapp.Repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findUserByEmail(email);
        if (user==null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return UserDetailsImpl.build(user);
    }
}
