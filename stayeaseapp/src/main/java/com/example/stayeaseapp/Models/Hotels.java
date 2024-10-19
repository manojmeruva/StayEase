package com.example.stayeaseapp.Models;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotels {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String hotelName;
    @NonNull
    String location;
    @NonNull
    int numberOfRoomsAvailable;

}
