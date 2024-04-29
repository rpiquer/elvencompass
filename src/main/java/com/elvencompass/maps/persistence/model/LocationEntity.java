package com.elvencompass.maps.persistence.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "location")
@Data
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    @Column(name="wiki_link")
    private String wikiLink;

    @Column(name="map_name")
    private String mapName;
    
    @OneToMany(mappedBy = "containerSubLocationEntity")
    private List<SubMarkerEntity> subMarkerEntityList;
}
