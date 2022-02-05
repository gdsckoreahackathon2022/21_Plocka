package com.jupging.jupgingServer.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRes {
    Long userId;
    String accessToken;
    boolean isNew;

    public SignInRes(Long userId, String accessToken, boolean isNew) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.isNew = isNew;
    }

}
