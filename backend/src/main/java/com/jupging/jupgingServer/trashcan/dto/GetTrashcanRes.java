package com.jupging.jupgingServer.trashcan.dto;

import com.jupging.jupgingServer.trashcan.domain.Trashcan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTrashcanRes {

    private Long trashCanId;
    private Double latitude;
    private Double longitude;

    public GetTrashcanRes(Trashcan trashcan){
        trashCanId = trashcan.getId();
        latitude = trashcan.getLatitude();
        longitude = trashcan.getLongitude();
    }
}
