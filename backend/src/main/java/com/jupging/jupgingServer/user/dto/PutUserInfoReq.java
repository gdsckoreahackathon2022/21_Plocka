package com.jupging.jupgingServer.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class PutUserInfoReq {
    String nickName;
    String profile;
    Float height;
    Float weight;
    String gender;

    public PutUserInfoReq(String nickName, String profile,
                          Float height, Float weight, String gender) {
        this.nickName = nickName;
        this.profile = profile;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
}
