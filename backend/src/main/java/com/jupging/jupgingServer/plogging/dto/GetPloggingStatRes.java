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
public class GetPloggingStatRes {

    private Double totalDistance = 0d;
    private int totalCalorie = 0;
    private int totalCount = 0;
    private int totalMin = 0;
    private int totalSec = 0;
    private List<PloggingInfo> ploggingList;

    public void sumTotalInfo(Double distance, int calorie, int min, int sec){
        this.totalDistance += distance;
        this.totalCalorie += calorie;
        this.totalCount += 1;
        this.totalMin += min;
        this.totalSec += sec;
    }
}
