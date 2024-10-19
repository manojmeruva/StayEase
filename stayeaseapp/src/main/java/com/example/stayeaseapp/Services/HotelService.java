package com.example.stayeaseapp.Services;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stayeaseapp.Models.Hotels;
import com.example.stayeaseapp.Repositories.HotelRepository;

@Service
public class HotelService {
    
    @Autowired
    HotelRepository hotelRepository;

    public void createHotel(Hotels hotel){
        hotelRepository.save(hotel);
    }

    public boolean updateHotel(Hotels hotel){
        Optional<Hotels> curHotel = hotelRepository.findById(hotel.getId());
        if(curHotel !=null){
            curHotel.get().setHotelName(hotel.getHotelName());
            curHotel.get().setLocation(hotel.getLocation());
            curHotel.get().setNumberOfRoomsAvailable(hotel.getNumberOfRoomsAvailable());
            hotelRepository.save(curHotel.get());
            return true;
        }
        return false;
    }

    public boolean deleteHotel(Hotels hotel){
        Optional<Hotels> curHotels = hotelRepository.findById(hotel.getId());
        if(curHotels!=null){
            hotelRepository.delete(curHotels.get());
            return true;
        }
        return false;
    }

    public List<Hotels> displayHotels(){
        
        return hotelRepository.findAll();
    }

    public Hotels getHotelById(Long id){
        return hotelRepository.findById(id).get();
    }
}
