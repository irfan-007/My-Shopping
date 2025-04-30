package com.myshopping.My.Shopping.RESPONSE_DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
}