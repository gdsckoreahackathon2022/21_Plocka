package com.jupging.jupgingServer.plogging.dto;

import lombok.*;

import java.math.BigInteger;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankInfo {

    private int rank;
    private Long userId;
    private String nickName;
    private String profile;
    private int count;
    private Double distance;

    public RankInfo(int rank, Long userId, int count, Double distance)
    {
        this.rank = rank;
        this.userId = userId;
        this.count = count;
        this.distance = distance;
    }

    public RankInfo(int rank, Long userId, String nickName, String profile)
    {
        this.rank = rank;
        this.userId = userId;
        this.nickName = nickName;
        this.profile = profile;
        this.count = 0;
        this.distance = 0d;
    }
}
