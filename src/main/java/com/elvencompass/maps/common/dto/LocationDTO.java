package com.elvencompass.maps.common.dto;

import java.util.List;

import lombok.Data;

@Data
public class LocationDTO {
    private Integer id;
    private String name;
    private String description;
    private String wikiLink;
    private String mapName;
    private List<MarkerDTO> markerDTOList;
}
