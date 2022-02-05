package com.jupging.jupgingServer.plogging.dto;

import com.jupging.jupgingServer.plogging.domain.Plogging;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PloggingInfo {

    private String routeImage;
    private String createdDate;
    private Double distance;
    private int calorie;
    private int minute;
    private int second;

    public PloggingInfo(Plogging plogging){
        this.routeImage = plogging.getRouteImage();
        this.createdDate = plogging.getCreatedDate().toString();
        this.distance = plogging.getDistance();
        this.calorie = plogging.getCalorie();
        this.minute = (plogging.getRunTimeHour()) * 60 + plogging.getRunTimeMin();
        this.second = plogging.getRunTimeSec();
    }
}
