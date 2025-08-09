package com.logship.user.serivce.mapper;

import com.logship.user.serivce.controller.request.SignupRequest;
import com.logship.user.serivce.dto.UserDTO;
import com.logship.user.serivce.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(request.getPassword()))")
    User mapSignUpToUser(SignupRequest request, @Context PasswordEncoder passwordEncoder);

    List<UserDTO> mapToDTO(List<User> users);

    UserDTO mapToDTO(User user);
}
