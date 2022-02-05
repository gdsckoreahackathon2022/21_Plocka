package com.jupging.jupgingServer.badge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class PutBadgeReq {
    Integer badgeId;
    LocalDate createTime;
}
