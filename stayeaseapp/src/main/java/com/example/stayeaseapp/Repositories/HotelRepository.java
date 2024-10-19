package com.example.stayeaseapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stayeaseapp.Models.Hotels;

@Repository
public interface HotelRepository extends JpaRepository<Hotels,Long> {

   
    
}
