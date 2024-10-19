package com.example.stayeaseapp.Services;

import java.time.LocalDateTime;
// import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stayeaseapp.Models.Hotels;
import com.example.stayeaseapp.Models.Register;
import com.example.stayeaseapp.Models.Users;
import com.example.stayeaseapp.Repositories.CheckInRepository;
import com.example.stayeaseapp.Repositories.HotelRepository;
import com.example.stayeaseapp.Repositories.UserRepository;

@Service
public class CheckInService {
    
    @Autowired
    BookRoomService bookRoomService;
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CheckInRepository checkInRepository;
    public void checkIn(Long id,String email){
        Optional<Hotels> hotel = hotelRepository.findById(id);
            Users user = userRepository.findUserByEmail(email);
            if(hotel != null && user!=null && user.isAlloted()){
                checkInRepository.save(new Register(user,LocalDateTime.now()));
            }
    }

    public void checkOut(Long id,String email){
        Optional<Hotels> hotel = hotelRepository.findById(id);
            Users user = userRepository.findUserByEmail(email);
            if(hotel != null && user!=null && user.isAlloted()){
                Optional<Register> userCheckout = checkInRepository.findById(id);
                userCheckout.get().setCheckOut(LocalDateTime.now());
                hotel.get().setNumberOfRoomsAvailable(hotel.get().getNumberOfRoomsAvailable()+1);
                hotelRepository.save(hotel.get());
                user.setAlloted(false);
                userRepository.save(user);
                checkInRepository.save(userCheckout.get());
            }
    }

}
