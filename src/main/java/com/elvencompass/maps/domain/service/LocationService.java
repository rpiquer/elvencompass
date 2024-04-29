package com.elvencompass.maps.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elvencompass.maps.common.dto.LocationDTO;

@Service
public interface LocationService {
    public List<LocationDTO> getAll();
    public long getTotalNumberOfRecords();
    public LocationDTO findByMapName(String mapName);
    public LocationDTO create(LocationDTO locationDTO);
    public LocationDTO update(LocationDTO locationDTO);
    public void delete(int id);
}
