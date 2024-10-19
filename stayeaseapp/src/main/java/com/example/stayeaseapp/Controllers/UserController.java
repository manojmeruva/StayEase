package com.example.stayeaseapp.Controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stayeaseapp.Config.JWTUtils;
import com.example.stayeaseapp.Exceptions.UserAlreadyExistsException;
import com.example.stayeaseapp.Models.Hotels;
import com.example.stayeaseapp.Models.Users;
import com.example.stayeaseapp.Services.BookRoomService;
import com.example.stayeaseapp.Services.CheckInService;
import com.example.stayeaseapp.Services.HotelService;
import com.example.stayeaseapp.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

        @Autowired
        UserService userService;

        @Autowired
        HotelService hotelService;

        @Autowired
        BookRoomService bookRoomService;

        @Autowired
        AuthenticationManager authenticationManager;

        @Autowired
        CheckInService checkInService;

        @Autowired
        JWTUtils jwtUtils;

        @PostMapping("/register")
        public ResponseEntity<Users> register(@RequestBody Users user) throws UserAlreadyExistsException{

                 userService.createUser(user);
                
                 return new ResponseEntity<>(HttpStatus.CREATED);

        }

        @GetMapping("/hotels")
        public ResponseEntity<List<Hotels>> getHotels(){
            return new ResponseEntity<>(hotelService.displayHotels(),HttpStatus.OK);
        }

        @PostMapping("/login")
    public String login(@RequestBody Users user) {
        // Authentication authentication;
        // try {
        // authentication = authenticationManager.authenticate(
        //             new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        //     );
       
        // } catch (AuthenticationException e) {
        //     return "Authentication failed: " + e.getMessage();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        return jwtUtils.generateJwtToken(authentication);
        }

        // SecurityContextHolder.getContext().setAuthentication(authentication);
        // String jwt = jwtUtils.generateJwtToken(authentication);
        // return jwt;



        @GetMapping("/")
        public ResponseEntity<List<Users>> getUsers(){
            return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
        }

        @PutMapping("/hotels/{id}/book")
        public ResponseEntity<Users> bookRoom(@RequestBody Users user,@PathVariable Long id){

                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
                String userEmail = authentication.getName();
                if(userEmail != null){
                bookRoomService.bookroom(id, userEmail);

                return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        @PutMapping("/")
        public ResponseEntity<Users> updateUser(@RequestBody Users user){
            userService.updateUser(user);
            return new ResponseEntity<>(user,HttpStatus.ACCEPTED);

        }
    }

       
        
    


        

