package com.elvencompass.maps.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MarkerRequest {
    private int id;
    private float latitude;
    private float longitude;
    @JsonProperty("referencedLocation")
    private LocationRequest referencedLocationRequest;
    @JsonProperty("containerLocation")
    private LocationRequest containerLocationRequest;
    private int userId;
}
