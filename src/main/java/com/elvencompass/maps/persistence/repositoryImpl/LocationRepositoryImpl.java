package com.elvencompass.maps.persistence.repositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.elvencompass.maps.domain.entity.Location;
import com.elvencompass.maps.domain.entity.Marker;
import com.elvencompass.maps.domain.repository.LocationRepository;
import com.elvencompass.maps.mapper.LocationMapper;
import com.elvencompass.maps.persistence.dao.LocationDAO;

@Repository
public class LocationRepositoryImpl implements LocationRepository{
    @Qualifier("LocationDAO")

    private final LocationDAO locationDAO;

    public LocationRepositoryImpl(LocationDAO locationDAO){
        this.locationDAO = locationDAO;
    }

    @Override
    public List<Location> getAll() {
        return LocationMapper.mapper.toLocationList(locationDAO.findAll());
    }

    @Override
    public Optional<Location> findByMapName(String mapName) {
        return Optional.ofNullable(LocationMapper.mapper.toLocation(locationDAO.findByMapName(mapName).orElse(null)));
    }

    @Override
    public Optional<Location> findById(int id) {
        return Optional.ofNullable(LocationMapper.mapper.toLocation(locationDAO.findById(id).orElse(null)));
    }

    @Override
    public long getTotalNumberOfRecords() {
        return locationDAO.count();
    }

    @Override
    public Location save(Location location) {
        System.out.println(location);
        if (location.getMarkerList()==null) {
            List<Marker> markerList = new ArrayList<>();
            location.setMarkerList(markerList);
            
        }
        return LocationMapper.mapper.toLocation(locationDAO.save(LocationMapper.mapper.toLocationEntity(location)));
    }

    @Override
    public void delete(Location location) {
        locationDAO.delete(LocationMapper.mapper.toLocationEntity(location));
    }
    
}
