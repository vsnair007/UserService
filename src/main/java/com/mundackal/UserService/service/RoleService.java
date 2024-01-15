package com.mundackal.UserService.service;

import com.mundackal.UserService.model.Role;
import com.mundackal.UserService.repo.RoleRepository;

public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name){
        Role role = new Role();
        role.setRole(name);
        role = roleRepository.save(role);
        return role;
    }
}
