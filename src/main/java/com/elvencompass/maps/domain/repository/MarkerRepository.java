package com.elvencompass.maps.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.elvencompass.maps.domain.entity.Marker;

@Component
public interface MarkerRepository {
     public Optional<Marker> findById(int id);
    public Marker save(Marker marker);
    public void delete(Marker marker);
}
