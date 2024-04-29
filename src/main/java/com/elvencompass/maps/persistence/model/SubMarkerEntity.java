package com.elvencompass.maps.persistence.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "marker")
@Data
public class SubMarkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float latitude;
    private float longitude;

    @ManyToOne
    @JoinColumn(name="container_location")
    private SubLocationEntity containerSubLocationEntity;
    private int userId;
}
