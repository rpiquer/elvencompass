package com.elvencompass.maps.controller.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LocationRequest {
    private Integer id;
    private String name;
    private String description;
    private String wikiLink;
    private String mapName;
    @JsonProperty("markers")
    private List<MarkerRequest> markerRequestList;
}
