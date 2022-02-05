package com.jupging.jupgingServer.badge.controller;

import com.jupging.jupgingServer.auth.jwt.JwtProvider;
import com.jupging.jupgingServer.badge.dto.GetBadgeRes;
import com.jupging.jupgingServer.badge.dto.PutBadgeReq;
import com.jupging.jupgingServer.badge.service.BadgeService;
import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;
    private final JwtProvider jwtProvider;

    @GetMapping("/{userId}/badge")
    public BaseResponse<GetBadgeRes> getBadge(@PathVariable Long userId)
    throws BaseException {
        Long id = jwtProvider.getUserIdx();
        GetBadgeRes getBadgeRes = badgeService.getBadge(id);
        return new BaseResponse<>(getBadgeRes);
    }

    @PutMapping("/{userId}/badge")
    public void putBadge(@PathVariable Long userId,
                         @RequestBody PutBadgeReq putBadgeReq)
    throws BaseException {
        Long id = jwtProvider.getUserIdx();
        badgeService.putBadge(id, putBadgeReq);

    }
}
