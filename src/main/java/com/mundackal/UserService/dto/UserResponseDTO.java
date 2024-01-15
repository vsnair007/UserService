package com.mundackal.UserService.dto;

import com.mundackal.UserService.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserResponseDTO {
    private String email;
    private Set<Role> roles = new HashSet<>();
}
