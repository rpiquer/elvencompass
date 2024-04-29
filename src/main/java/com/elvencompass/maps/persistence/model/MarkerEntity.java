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

    @OneToOne
    @JoinColumn(name="referenced_location")
    private SubLocationEntity referencedSubLocationEntity;

    @ManyToOne
    @JoinColumn(name="container_location")
    private SubLocationEntity ContainerSubLocationEntity;
    private int userId;
}
