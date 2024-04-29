package com.elvencompass.maps.domain.service.impl;

import org.springframework.stereotype.Service;

import com.elvencompass.maps.common.dto.MarkerDTO;
import com.elvencompass.maps.common.exception.ResourceNotFoundException;
import com.elvencompass.maps.domain.entity.Marker;
import com.elvencompass.maps.domain.repository.MarkerRepository;
import com.elvencompass.maps.domain.service.MarkerService;
import com.elvencompass.maps.mapper.MarkerMapper;

@Service
public class MarkerServiceImpl implements MarkerService{
    private MarkerRepository markerRepository;

    public MarkerServiceImpl(MarkerRepository markerRepository){
        this.markerRepository = markerRepository;
    }

    public Marker findById(int id){
        return markerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marker not found with id: " + id));
    }

    @Override
    public MarkerDTO create(MarkerDTO markerDTO) {
        Marker marker = MarkerMapper.mapper.toMarker(markerDTO);
        return MarkerMapper.mapper.toMarkerDTO(markerRepository.save(marker));
    }

    @Override
    public void delete(int id) {
        markerRepository.delete(this.findById(id));
    }
    
}
