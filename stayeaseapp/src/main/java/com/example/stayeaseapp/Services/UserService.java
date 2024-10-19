package com.example.stayeaseapp.Services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.stayeaseapp.Exceptions.UserAlreadyExistsException;
import com.example.stayeaseapp.Models.Roles;
import com.example.stayeaseapp.Models.Users;
import com.example.stayeaseapp.Repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void createUser(Users user) throws UserAlreadyExistsException{
            if(userRepository.findUserByEmail(user.getEmail())==null){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList("CUSTOMER"));
                userRepository.save(user);
            }

            else throw new UserAlreadyExistsException("User Already Exists.");
    }

    public void updateUser(Users user){
        Users curuserdetails = userRepository.findUserByEmail(user.getEmail());
        curuserdetails.setEmail(user.getEmail());
        curuserdetails.setFirstName(user.getFirstName());
        curuserdetails.setLastName(user.getLastName());
        curuserdetails.setRoles(user.getRoles());
        userRepository.save(curuserdetails);
    }

    public List<Users> getUsers(){
        return userRepository.findAll();
    }
}
