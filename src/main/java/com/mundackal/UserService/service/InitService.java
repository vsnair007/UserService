package com.mundackal.UserService.service;

import com.mundackal.UserService.repo.RoleRepository;
import com.mundackal.UserService.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private RoleRepository roleRepository;
    private UserRepository userRepository;


    public void initialize() {
    }
}
