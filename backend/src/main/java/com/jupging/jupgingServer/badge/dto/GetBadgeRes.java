package com.jupging.jupgingServer.badge.dto;

import com.jupging.jupgingServer.badge.domain.Badge;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
public class GetBadgeRes {
    List<Integer> badgeList;

    public GetBadgeRes(List<Integer> badgeList) {
        this.badgeList = badgeList;
    }
}
