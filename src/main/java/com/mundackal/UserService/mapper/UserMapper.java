package com.mundackal.UserService.mapper;

import com.mundackal.UserService.dto.UserResponseDTO;
import com.mundackal.UserService.model.User;

public class UserMapper {
    public static UserResponseDTO UserToUserResponseDTOMapper(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRoles(user.getRoles());
        return userResponseDTO;
    }
}
