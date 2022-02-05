package com.jupging.jupgingServer.like.service;

import com.jupging.jupgingServer.like.domain.Like;
import com.jupging.jupgingServer.like.dto.PostLikeReq;
import com.jupging.jupgingServer.like.repository.LikeRepository;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public boolean like(Long userId, Long fromUserId, PostLikeReq postLikeReq){
        User fromUser = userRepository.findById(fromUserId)
            .orElseThrow();
        User user = userRepository.findById(userId)
            .orElseThrow();

        Like like = likeRepository.findByUserIdAndUserId(userId, fromUser.getId())
                        .map(Like::update).orElse(Like.of(fromUser));

        like.setUser(user);
        user.updateLikeCount(postLikeReq.getIsLike());
        likeRepository.save(like);
        return like.getLikeState();
    }
}
