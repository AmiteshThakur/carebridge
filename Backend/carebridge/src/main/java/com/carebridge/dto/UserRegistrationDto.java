package com.carebridge.dto;



import com.carebridge.Enum.UserEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDto {
    private String name;
    private String password;
    private String email;
    private UserEnum.Role role;

}
