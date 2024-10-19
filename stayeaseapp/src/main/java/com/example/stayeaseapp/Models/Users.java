package com.example.stayeaseapp.Models;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String email;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    String password;
    List<String> roles ;
    boolean alloted = false;

}
