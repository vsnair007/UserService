package com.mundackal.UserService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RolesRequestDTO {
    List<UUID> roles;
}
