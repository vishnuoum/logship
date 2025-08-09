package com.logship.user.serivce.dto;

import com.logship.user.serivce.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String employeeName;
    private Role role;
}
