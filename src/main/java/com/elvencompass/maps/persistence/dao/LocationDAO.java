package com.elvencompass.maps.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elvencompass.maps.persistence.model.LocationEntity;


public interface LocationDAO extends JpaRepository <LocationEntity, Integer>{
    Optional<LocationEntity> findByMapName(String mapName);
}
