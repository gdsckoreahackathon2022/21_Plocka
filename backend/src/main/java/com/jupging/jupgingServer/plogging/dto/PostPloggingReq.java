package com.jupging.jupgingServer.plogging.dto;

import com.jupging.jupgingServer.plogging.domain.Plogging;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PostPloggingReq {

    private MultipartFile routeImage;
    private MultipartFile trashImage;
    @NotNull
    private Double distance;
    @NotNull
    private int calorie;
    @NotNull
    private int avgPaceMin;
    @NotNull
    private int avgPaceSec;
    @NotNull
    private int runTimeHour;
    @NotNull
    private int runTimeMin;
    @NotNull
    private int runTimeSec;

}
