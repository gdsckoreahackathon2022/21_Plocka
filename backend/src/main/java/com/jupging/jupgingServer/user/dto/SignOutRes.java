package com.jupging.jupgingServer.user.dto;

import com.jupging.jupgingServer.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignOutRes {
    List<User> users;

    public SignOutRes(List<User> users) {
        this.users = users;
    }
}
