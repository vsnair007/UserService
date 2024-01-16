package com.mundackal.UserService.controller;

import com.mundackal.UserService.dto.LoginRequestDTO;
import com.mundackal.UserService.dto.UserResponseDTO;
import com.mundackal.UserService.exception.InvalidTokenException;
import com.mundackal.UserService.exception.PassswordNotMatchingException;
import com.mundackal.UserService.exception.SessionNotFoundException;
import com.mundackal.UserService.exception.UserNotFoundException;
import com.mundackal.UserService.model.Session;
import com.mundackal.UserService.model.SessionStatus;
import com.mundackal.UserService.model.User;
import com.mundackal.UserService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws UserNotFoundException, PassswordNotMatchingException {
        return new ResponseEntity(authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/logout/{id}")
    public ResponseEntity<String> logout(@PathVariable UUID id , @RequestHeader("token") String token) throws SessionNotFoundException {
        authService.logout(token,id);
        return new ResponseEntity(new String("Successfully Logged Out"),HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestHeader("token")String token,UUID id) throws InvalidTokenException {
        return new ResponseEntity(authService.validate(token,id),HttpStatus.OK);
    }

    //Test API's
    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getAllSessions(){
        return authService.getAllSessions();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return authService.getAllUsers();
    }

}
