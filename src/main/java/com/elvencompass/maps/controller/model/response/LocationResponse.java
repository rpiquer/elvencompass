package com.elvencompass.maps.controller.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LocationResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String wikiLink;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mapName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("markers")
    private List<MarkerResponse> markerResponseList;
}
