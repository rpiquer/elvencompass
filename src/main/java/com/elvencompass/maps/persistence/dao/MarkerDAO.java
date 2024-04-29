package com.elvencompass.maps.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elvencompass.maps.persistence.model.MarkerEntity;

public interface MarkerDAO extends JpaRepository<MarkerEntity, Integer> {
    
}
