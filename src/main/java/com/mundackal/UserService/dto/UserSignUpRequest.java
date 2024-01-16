package com.mundackal.UserService.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class UserSignUpRequest {
    private String email;
    private String password;
}
