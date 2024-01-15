package com.mundackal.UserService.service;

import com.mundackal.UserService.dto.UserResponseDTO;
import com.mundackal.UserService.exception.UserNotFoundException;
import com.mundackal.UserService.model.Role;
import com.mundackal.UserService.model.User;
import com.mundackal.UserService.repo.RoleRepository;
import com.mundackal.UserService.repo.UserRepository;
import org.springframework.stereotype.Service;

import com.mundackal.UserService.exception.RoleNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.mundackal.UserService.mapper.UserMapper.UserToUserResponseDTOMapper;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO getUser(UUID id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("No User found with id "+id));
        return UserToUserResponseDTOMapper(user);
    }
    public UserResponseDTO setUserRole(UUID userId, List<UUID> roleIds) throws UserNotFoundException, RoleNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("No User found with id "+userId));
        Set<Role> roles = roleRepository.findAllByIdIn(roleIds).orElseThrow(()->new RoleNotFoundException("No Role found with id "+roleIds));
        user.setRoles(roles);
        User updatedUser = userRepository.save(user);
        return UserToUserResponseDTOMapper(updatedUser);
    }

}
