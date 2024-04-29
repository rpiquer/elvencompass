package com.elvencompass.maps.domain.entity;

import java.util.List;

import lombok.Data;

@Data
public class Location {
    private Integer id;
    private String name;
    private String description;
    private String wikiLink;
    private String mapName;
    private List<Marker> markerList;
}
