package com.jupging.jupgingServer.like.controller;

import com.jupging.jupgingServer.auth.annotation.LoginUser;
import com.jupging.jupgingServer.auth.jwt.JwtProvider;
import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.common.BaseResponse;
import com.jupging.jupgingServer.like.dto.PostLikeReq;
import com.jupging.jupgingServer.like.service.LikeService;
import com.jupging.jupgingServer.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final JwtProvider jwtProvider;

    @PostMapping("/{userId}/likes")
    public void like(@PathVariable Long userId, @RequestBody PostLikeReq postLikeReq)
    throws BaseException {
        Long fromUserId = jwtProvider.getUserIdx();
        // userId - 좋아요 당하는 사람
        // fromUserId - 좋아요 누르는 사람 (현재 로그인 되어 있는 사람)
        likeService.like(userId, fromUserId, postLikeReq);
    }
}
