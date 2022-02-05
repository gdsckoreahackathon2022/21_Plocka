package com.jupging.jupgingServer.user.controller;

import com.jupging.jupgingServer.auth.jwt.JwtProvider;
import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.common.BaseResponse;
import com.jupging.jupgingServer.user.dto.*;
import com.jupging.jupgingServer.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @PostMapping("/sign-in")
    public BaseResponse<SignInRes> signIn(@RequestBody SignInReq signInReq) {
        SignInRes signInRes = authService.signIn(signInReq);
        return new BaseResponse<>(signInRes);
    }

    @PutMapping("/sign-up")
    public BaseResponse<SignUpRes> signUp(@RequestBody SignUpReq signUpReq) throws Exception {
        Long userId = jwtProvider.getUserIdx();
        SignUpRes signUpRes = authService.signUp(userId, signUpReq);
        return new BaseResponse<>(signUpRes);
    }

    @DeleteMapping("/sign-out")
    public BaseResponse<SignOutRes> signOut() throws BaseException  {
        Long userId = jwtProvider.getUserIdx();
        SignOutRes signOutRes = authService.signOut(userId);
        return new BaseResponse<>(signOutRes);
    }
}
