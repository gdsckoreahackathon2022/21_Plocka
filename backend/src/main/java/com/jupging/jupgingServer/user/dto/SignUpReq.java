package com.jupging.jupgingServer.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SignUpReq {
    String nickName;
    // MultipartFile profile;
    String profile;

    public SignUpReq(String nickName, String profile) {
        this.nickName = nickName;
        this.profile = profile;
    }
}
