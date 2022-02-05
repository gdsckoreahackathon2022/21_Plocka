package com.jupging.jupgingServer.badge.service;

import com.jupging.jupgingServer.badge.domain.Badge;
import com.jupging.jupgingServer.badge.dto.GetBadgeRes;
import com.jupging.jupgingServer.badge.dto.PutBadgeReq;
import com.jupging.jupgingServer.badge.repository.BadgeRepository;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.repository.UserRepository;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;

    public GetBadgeRes getBadge(Long userId) {
        List<Badge> badgeList = badgeRepository.findByUserId(userId);
        List<Integer> badges= new ArrayList<>(
            Collections.nCopies(11, 0));

        for(Badge badge : badgeList) {
            badges.remove(badge.getId().intValue());
            badges.add(badge.getId().intValue(), 1);
        }
        return new GetBadgeRes(badges);
    }

    public void putBadge(Long userId, PutBadgeReq putBadgeReq) {
        User user = userRepository.findById(userId)
            .orElseThrow();
        badgeRepository.save(new Badge(
            Long.valueOf(putBadgeReq.getBadgeId()), user
        ));
    }
}
