package com.elvencompass.maps.domain.service;

import org.springframework.stereotype.Service;

import com.elvencompass.maps.common.dto.MarkerDTO;

@Service
public interface MarkerService {
    public MarkerDTO create(MarkerDTO markerDTO);
    public MarkerDTO delete(int id);
}
