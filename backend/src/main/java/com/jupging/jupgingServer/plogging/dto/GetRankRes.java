package com.jupging.jupgingServer.plogging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRankRes {
    private RankInfo myRank;
    private List<RankInfo> rankList;

    public void setMyRank(int rank, int count, double distance){
        this.myRank.setRank(rank);
        this.myRank.setCount(count);
        this.myRank.setDistance(distance);
    }
}
