package com.evo.springboot.app.Jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class jwtRequest {
    private String userName;
    private String password;
}
