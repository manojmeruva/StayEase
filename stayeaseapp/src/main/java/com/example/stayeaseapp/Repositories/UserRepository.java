package com.example.stayeaseapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.stayeaseapp.Models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,String>{
    Users findUserByEmail(String email);
}
