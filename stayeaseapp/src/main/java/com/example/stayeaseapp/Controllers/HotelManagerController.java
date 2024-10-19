package com.example.stayeaseapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stayeaseapp.Models.Hotels;
import com.example.stayeaseapp.Models.Users;
import com.example.stayeaseapp.Services.BookRoomService;
import com.example.stayeaseapp.Services.CheckInService;
import com.example.stayeaseapp.Services.HotelService;

@RestController
@RequestMapping("/manager")
public class HotelManagerController {


    @Autowired
    BookRoomService bookRoomService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CheckInService checkInService;

    @Autowired
    HotelService hotelService;

     @PutMapping("/checkIn/{id}")
        public ResponseEntity<String> checkIn(@RequestBody Users user,@PathVariable Long id){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
                    String userEmail = authentication.getName();

                    if(userEmail != null){
                        checkInService.checkIn(id, userEmail);
        
                        return new ResponseEntity<>(HttpStatus.OK);
                        }
                        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }

        @PutMapping("/hotel/")
    public ResponseEntity<String> updateHotelDetails(@RequestBody Hotels hotel){
            hotelService.updateHotel(hotel);
            return new ResponseEntity<>(HttpStatus.OK);
    }

        @PutMapping("hotel/{id}/{email}")
        public ResponseEntity<String> cancelBooking(@PathVariable Long id , @PathVariable String email){
            bookRoomService.cancelBooking(id, email);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PutMapping("/checkOut/{id}")
        public ResponseEntity<String> checkOut(@RequestBody Users user,@PathVariable Long id){
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
                    String userEmail = authentication.getName();

                    if(userEmail != null){
                        checkInService.checkOut(id, userEmail);
        
                        return new ResponseEntity<>(HttpStatus.OK);
                        }
                        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
}
