package com.logship.user.serivce.service;

import com.logship.user.serivce.dto.UserDTO;
import com.logship.user.serivce.entity.User;
import com.logship.user.serivce.exception.ExceptionManager;
import com.logship.user.serivce.mapper.UserMapper;
import com.logship.user.serivce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapToDTO(users);
    }

    public UserDTO getUser(String username) {
        return userRepository.findByUsername(username).map(userMapper::mapToDTO)
                .orElseThrow(() -> ExceptionManager.throwException(
                        ExceptionManager.ERRORCODE.USER_NOT_FOUND_ERROR));
    }
}
