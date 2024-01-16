package com.mundackal.UserService.controller;

import com.mundackal.UserService.dto.RoleRequestDTO;
import com.mundackal.UserService.model.Role;
import com.mundackal.UserService.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<List<Role>> createRole(@RequestBody RoleRequestDTO roleRequestDTO){
        Role role = roleService.createRole(roleRequestDTO.getName());
        return new ResponseEntity(role, HttpStatus.OK);
    }
}
