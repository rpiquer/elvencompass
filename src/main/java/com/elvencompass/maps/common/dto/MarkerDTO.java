package com.elvencompass.maps.common.dto;

import lombok.Data;

@Data
public class MarkerDTO {
    private int id;
    private float latitude;
    private float longitude;
    private LocationDTO referencedLocationDTO;
    private LocationDTO containerLocationDTO;
    private int userId;
}
