package com.jupging.jupgingServer.user.controller;

import com.jupging.jupgingServer.auth.annotation.LoginUser;
import com.jupging.jupgingServer.auth.jwt.JwtProvider;
import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.common.BaseResponse;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.dto.GetUserInfoRes;
import com.jupging.jupgingServer.user.dto.PutUserInfoReq;
import com.jupging.jupgingServer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PutMapping("/{userId}")
    public void putUserInfo(@PathVariable Long userId, @RequestBody PutUserInfoReq putUserInfoReq)
    throws BaseException {
        Long id = jwtProvider.getUserIdx();
        userService.putUserInfo(id, putUserInfoReq);
    }

    @GetMapping("/{userId}/info")
    public BaseResponse<GetUserInfoRes> getUserInfo(@PathVariable Long userId)
        throws BaseException {
        Long id = jwtProvider.getUserIdx();
        GetUserInfoRes getUserInfoRes = userService.getUserInfo(userId);
        return new BaseResponse<>(getUserInfoRes);
    }
}
