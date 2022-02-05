package com.jupging.jupgingServer.user.service;

import com.jupging.jupgingServer.auth.jwt.JwtProvider;
import com.jupging.jupgingServer.common.BaseException;
import com.jupging.jupgingServer.common.GCSuploader;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.dto.*;
import com.jupging.jupgingServer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.transaction.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final GCSuploader gcSuploader;

    private static final String DIR_USER = "user";

    public SignInRes signIn(SignInReq signInReq) {
        User user = userRepository.findByEmail(signInReq.getEmail());

        boolean isNew = false;
        if(user == null) {
            user = userRepository.save(new User(signInReq.getEmail()));
            isNew = true;
        }

        String accessToken = jwtProvider.createJwt(user.getId());
        return new SignInRes(user.getId(), accessToken, isNew);
    }

    public SignUpRes signUp(Long userId, SignUpReq signUpReq) throws Exception {
        User user = userRepository.findById(userId).orElseThrow();

       //String profile = gcSuploader.uploadFile(signUpReq.getProfile(), DIR_USER);
        User updateUser = user.update(signUpReq.getNickName(), signUpReq.getProfile());
        User saveUser = userRepository.save(updateUser);

        return new SignUpRes(saveUser.getId());
    }

    public SignOutRes signOut(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
        return new SignOutRes(userRepository.findAll());
    }
}
