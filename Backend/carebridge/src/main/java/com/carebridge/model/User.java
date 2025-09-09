package com.carebridge.model;

import com.carebridge.Enum.UserEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String name;
    private String contact;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserEnum.Role role;
    private String address;
    private String city;
    private String state;
    private String country;


}

