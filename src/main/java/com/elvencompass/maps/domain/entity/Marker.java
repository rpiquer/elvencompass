package com.elvencompass.maps.domain.entity;

import lombok.Data;

@Data
public class Marker {
    private int id;
    private float latitude;
    private float longitude;
    private Location referencedLocation;
    private Location containerLocation;
    private int userId;
}
