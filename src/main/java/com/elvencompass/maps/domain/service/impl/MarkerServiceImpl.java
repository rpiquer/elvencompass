package com.elvencompass.maps.domain.service.impl;

import org.springframework.stereotype.Service;

import com.elvencompass.maps.common.dto.MarkerDTO;
import com.elvencompass.maps.common.exception.ResourceNotFoundException;
import com.elvencompass.maps.domain.entity.Location;
import com.elvencompass.maps.domain.entity.Marker;
import com.elvencompass.maps.domain.repository.LocationRepository;
import com.elvencompass.maps.domain.repository.MarkerRepository;
import com.elvencompass.maps.domain.service.MarkerService;
import com.elvencompass.maps.mapper.MarkerMapper;

@Service
public class MarkerServiceImpl implements MarkerService{
    private MarkerRepository markerRepository;
    private LocationRepository locationRepository;

    public MarkerServiceImpl(MarkerRepository markerRepository, LocationRepository locationRepository){
        this.markerRepository = markerRepository;
        this.locationRepository = locationRepository;
    }

    public Marker findById(int id){
        return markerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marker not found with id: " + id));
    }

    @Override
    public MarkerDTO create(MarkerDTO markerDTO) {
        Marker marker = MarkerMapper.mapper.toMarker(markerDTO);
        Location location = locationRepository.save(marker.getReferencedLocation());
        marker.setReferencedLocation(location);
        return MarkerMapper.mapper.toMarkerDTO(markerRepository.save(marker));
    }

    @Override
    public MarkerDTO delete(int id) {
        Marker marker = this.findById(id);
        MarkerDTO markerDTO = MarkerMapper.mapper.toMarkerDTO(markerRepository.delete(marker));
        locationRepository.delete(locationRepository.findById(marker.getReferencedLocation().getId()).orElseThrow(() -> new ResourceNotFoundException("Location not found") ));
        return markerDTO;
    }
    
}
