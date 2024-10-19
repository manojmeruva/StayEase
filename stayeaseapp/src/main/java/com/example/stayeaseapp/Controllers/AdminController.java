package com.example.stayeaseapp.Controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stayeaseapp.Models.Hotels;
import com.example.stayeaseapp.Services.HotelService;

@RestController
@RequestMapping("/admin")
public class AdminController {



    @Autowired
    HotelService hotelService;


    
    @PostMapping("/hotel")
    public ResponseEntity<Hotels> createHotelDetails(@RequestBody Hotels hotel){
        
        hotelService.createHotel(hotel);
        return new ResponseEntity<>(hotel,HttpStatus.CREATED);
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<String> deleteHotelDetails(@PathVariable Long id){
        hotelService.deleteHotel(hotelService.getHotelById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
