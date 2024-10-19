package com.example.stayeaseapp.Models;



import java.time.LocalDateTime;
// import java.util.Date;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Register")
public class Register {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Users user;
    @NonNull
    LocalDateTime checkIn;
    LocalDateTime checkOut;
    public Register(Users user,LocalDateTime localDateTime){
        this.user=user;
        this.checkIn= localDateTime;
    }
}
