package com.example.stayeaseapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stayeaseapp.Models.Register;

@Repository
public interface CheckInRepository extends JpaRepository<Register,Long>{
    
}
