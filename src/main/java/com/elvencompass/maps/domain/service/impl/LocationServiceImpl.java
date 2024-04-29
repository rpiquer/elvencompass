package com.elvencompass.maps.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elvencompass.maps.common.dto.LocationDTO;
import com.elvencompass.maps.common.exception.ResourceNotFoundException;
import com.elvencompass.maps.domain.entity.Location;
import com.elvencompass.maps.domain.repository.LocationRepository;
import com.elvencompass.maps.domain.service.LocationService;
import com.elvencompass.maps.mapper.LocationMapper;

@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }
    public List<LocationDTO> getAll(){
        return LocationMapper.mapper.toLocationDTOList(locationRepository.getAll());
    }

    public long getTotalNumberOfRecords(){
        return locationRepository.getTotalNumberOfRecords();
    }

    public LocationDTO findByMapName(String mapName){
        Location location = locationRepository.findByMapName(mapName).orElseThrow(() -> new ResourceNotFoundException("Location not found with map name: " + mapName));
        return LocationMapper.mapper.toLocationDTO(location);
    }

    public Location findById(int id){
        return locationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
    }

    public LocationDTO create(LocationDTO locationDTO){
        Location location = LocationMapper.mapper.toLocation(locationDTO);
        return LocationMapper.mapper.toLocationDTO(locationRepository.save(location));
    }

    public LocationDTO update(LocationDTO locationDTO){
        Location location = this.findById(locationDTO.getId());
        LocationMapper.mapper.updateLocationFromLocationDTO(locationDTO, location);
        return LocationMapper.mapper.toLocationDTO(locationRepository.save(location));
    }

    public void delete(int id){
        locationRepository.delete(this.findById(id));
    }
}
