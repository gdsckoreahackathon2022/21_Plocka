package com.jupging.jupgingServer.user.dto;

import com.jupging.jupgingServer.badge.domain.Badge;
import com.jupging.jupgingServer.plogging.domain.Plogging;
import com.jupging.jupgingServer.user.domain.User;
import com.jupging.jupgingServer.user.domain.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoRes {
    String nickName;
    String profile;
    int level;
    List<Plogging> ploggingList;
    int likeCount;
    List<Integer> badgeList;
    float height;
    float weight;
    String gender;

    public GetUserInfoRes(User user, List<Plogging> ploggingList,
                          List<Integer> badgeList) {
        this.nickName = user.getNickName();
        this.profile = user.getPicture();
        this.level = user.getLevel();
        this.ploggingList = ploggingList;
        this.likeCount = user.getLikeCount();
        this.badgeList = badgeList;
        this.height = getHeight();
        this.weight = getWeight();

        if(user.getGenderType() == GenderType.MALE) this.gender = "male";
        else this.gender = "female";
    }


}
