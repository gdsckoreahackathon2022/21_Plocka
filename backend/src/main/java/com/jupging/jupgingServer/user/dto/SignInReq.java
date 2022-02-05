package com.jupging.jupgingServer.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInReq {
    String email;

    public SignInReq(String email){
        this.email = email;
    }
}
