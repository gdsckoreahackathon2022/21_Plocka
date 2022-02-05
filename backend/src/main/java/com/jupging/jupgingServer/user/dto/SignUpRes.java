package com.jupging.jupgingServer.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRes {
    Long userId;

    public SignUpRes(Long userId) {
        this.userId = userId;

    }
}
