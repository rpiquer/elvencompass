package com.elvencompass.maps.persistence.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "marker")
@Data
public class MarkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float latitude;
    private float longitude;

    @Column(name="referenced_location")
    private int referencedLocationId;

    @Column(name="container_location")
    private int containerLocationId;
    private int userId;
}
