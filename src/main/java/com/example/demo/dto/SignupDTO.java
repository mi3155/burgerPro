package com.example.demo.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignupDTO {
    private String id;
    private String password;
    private String address;
    private int phone;
    private String auth;
}
