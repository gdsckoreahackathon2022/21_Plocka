package com.jupging.jupgingServer.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRes {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthRes(String accessToken) {
        this.accessToken = accessToken;
    }
}
