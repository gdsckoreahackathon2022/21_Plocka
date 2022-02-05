package com.jupging.jupgingServer.user.service;

import com.jupging.jupgingServer.badge.domain.Badge;
import com.jupging.jupgingServer.badge.repository.BadgeRepository;
import com.jupging.jupgingServer.plogging.domain.Plogging;
import com.jupging.jupgingServer.plogging.repository.PloggingRepository;
import com.jupging.jupgingServer.plogging.service.PloggingService;
import com.jupging.jupgingServer.trashcan.repository.TrashcanRepository;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.domain.enums.GenderType;
import com.jupging.jupgingServer.user.dto.GetUserInfoRes;
import com.jupging.jupgingServer.user.dto.PutUserInfoReq;
import com.jupging.jupgingServer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;
    private final PloggingRepository ploggingRepository;
    private final PloggingService ploggingService;
    private final TrashcanRepository trashcanRepository;

    public void putUserInfo(Long userId, PutUserInfoReq req) {
        User user = userRepository.findById(userId)
            .orElseThrow();

        GenderType gender;
        if(req.getGender() == "male") gender = GenderType.MALE;
        else gender = GenderType.FEMALE;

        user.update(req.getNickName(), req.getProfile(),
            req.getHeight(), req.getWeight(), gender);
    }

    public GetUserInfoRes getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        List<Plogging> ploggingList = ploggingRepository.findByUserId(user.getId());
        List<Badge> badgeList = badgeRepository.findByUserId(user.getId());

        int level = checkedLevel(ploggingList);
        List<Integer> badges = checkedBadge(user, ploggingList, badgeList);
        user.updateLevel(level);

        return new GetUserInfoRes(user, ploggingList, badges);
    }

    public int checkedLevel(List<Plogging> ploggingList) {
        int count = 0;
        for(Plogging plogging : ploggingList) {
            if(plogging.getTrashImage() != null && plogging.getDistance() >= 1)
                count += 1;
        }

        if (count < 5) return 0;
        else if (count < 10) return 1;
        else if (count < 15) return 2;
        else if (count < 30) return 3;
        else if (count < 70) return 4;
        else if (count < 150) return 5;
        else return 6;
    }

    public List<Integer> checkedBadge(User user, List<Plogging> ploggingList, List<Badge> badgeList) {
        List<Integer> badges= new ArrayList<>(
            Collections.nCopies(11, 0));

        int count = 0;
        for(Plogging plogging : ploggingList) {
            if(plogging.getTrashImage() != null && plogging.getDistance() >= 1)
                count += 1;
        }

        Double totalDistance = ploggingService.getPloggingStat(user.getId()).getTotalDistance();
        Integer trashcanCnt = trashcanRepository.findByUserId(user.getId()).size();

        if (count > 0) insertBadge(0, badges);
        if (count > 9) insertBadge(1, badges);
        if (count > 99) insertBadge(2, badges);
        if (trashcanCnt > 9) insertBadge(3, badges);
        if (trashcanCnt > 29) insertBadge(4, badges);
        if (totalDistance > 1100) insertBadge(5, badges);
        if (totalDistance > 42000) insertBadge(6, badges);
        if (user.getLikeCount() > 9) insertBadge(7, badges);
        if (user.getLikeCount() > 29) insertBadge(8, badges);
        return badges;

    }

    public List<Integer> insertBadge(Integer badgeId, List<Integer> badges) {
        badges.remove(badgeId);
        badges.add(badgeId, 1);
        return badges;
    }

}
