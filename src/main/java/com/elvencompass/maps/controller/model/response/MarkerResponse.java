package com.elvencompass.maps.controller.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MarkerResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float latitude;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private float longitude;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("referencedLocation")
    private LocationResponse referencedLocationResponse;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("containerLocation")
    private LocationResponse containerLocationResponse;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int userId;
}
