package com.jupging.jupgingServer.trashcan.dto;

import org.hibernate.validator.constraints.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostTrashcanReq {

    @Range(min=-90,max=90)
    private Double latitude;
    @Range(min=-180,max=180)
    private Double longitude;
}
