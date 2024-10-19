package com.example.stayeaseapp.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stayeaseapp.Models.Hotels;
import com.example.stayeaseapp.Models.Users;
import com.example.stayeaseapp.Repositories.HotelRepository;
import com.example.stayeaseapp.Repositories.UserRepository;

@Service
public class BookRoomService {
    
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    UserRepository userRepository;

    public void bookroom(Long id,String email){
            Optional<Hotels> hotel = hotelRepository.findById(id);
            Users user = userRepository.findUserByEmail(email);
            if(hotel != null && hotel.get().getNumberOfRoomsAvailable()>0 ){
                hotel.get().setNumberOfRoomsAvailable(hotel.get().getNumberOfRoomsAvailable()-1);
                hotelRepository.save(hotel.get());
                user.setAlloted(true);
                userRepository.save(user);
            }
    }

    public void cancelBooking(Long id,String email){
        Optional<Hotels> hotel = hotelRepository.findById(id);
           Users user = userRepository.findUserByEmail(email);
           if(hotel != null && hotel.get().getNumberOfRoomsAvailable()>0 ){
               hotel.get().setNumberOfRoomsAvailable(hotel.get().getNumberOfRoomsAvailable()+1);
               hotelRepository.save(hotel.get());
               user.setAlloted(false);
               userRepository.save(user);
           }
   }
}
