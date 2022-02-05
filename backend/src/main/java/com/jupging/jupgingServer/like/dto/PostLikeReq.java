package com.jupging.jupgingServer.like.dto;

import com.jupging.jupgingServer.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
public class PostLikeReq {
    Long userId;
    Boolean isLike;

    public PostLikeReq(Long userId, Boolean isLike) {
        this.userId = userId;
        this.isLike = isLike;
    }
}
