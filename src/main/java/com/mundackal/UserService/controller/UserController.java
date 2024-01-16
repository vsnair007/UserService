package com.mundackal.UserService.controller;

import com.mundackal.UserService.dto.RolesRequestDTO;
import com.mundackal.UserService.dto.UserResponseDTO;
import com.mundackal.UserService.exception.RoleNotFoundException;
import com.mundackal.UserService.exception.UserNotFoundException;
import com.mundackal.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable UUID id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserResponseDTO> setUserRoles(@PathVariable UUID id,@RequestBody RolesRequestDTO rolesRequestDTO) throws UserNotFoundException, RoleNotFoundException {
        return new ResponseEntity(userService.setUserRole(id, rolesRequestDTO.getRoles()), HttpStatus.OK);
    }
}
