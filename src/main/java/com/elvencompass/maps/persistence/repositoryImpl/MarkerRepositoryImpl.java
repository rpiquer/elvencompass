package com.elvencompass.maps.persistence.repositoryImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.elvencompass.maps.domain.entity.Location;
import com.elvencompass.maps.domain.entity.Marker;
import com.elvencompass.maps.domain.repository.MarkerRepository;
import com.elvencompass.maps.mapper.MarkerMapper;
import com.elvencompass.maps.persistence.dao.MarkerDAO;
import com.elvencompass.maps.persistence.model.MarkerEntity;

@Repository
public class MarkerRepositoryImpl implements MarkerRepository{
    @Qualifier("MarkerDAO")

    private final MarkerDAO markerDAO;

    public MarkerRepositoryImpl(MarkerDAO markerDAO){
        this.markerDAO = markerDAO;
    }

    @Override
    public Optional<Marker> findById(int id) {
        MarkerEntity markerEntity = markerDAO.findById(id).orElse(null);
        Marker marker = MarkerMapper.mapper.toMarker(markerEntity);
        Location location = new Location();
        location.setId(markerEntity.getContainerLocationId());
        marker.setContainerLocation(location);
        location.setId(markerEntity.getReferencedLocationId());
        marker.setReferencedLocation(location);
        return Optional.ofNullable(marker);
    }

    @Override
    public Marker save(Marker marker) {
        MarkerEntity markerEntity = MarkerMapper.mapper.toMarkerEntity(marker);
        markerEntity.setContainerLocationId(marker.getContainerLocation().getId());
        markerEntity.setReferencedLocationId(marker.getReferencedLocation().getId());
        markerDAO.save(markerEntity);
        Marker savedMarker = MarkerMapper.mapper.toMarker(markerEntity);
        Location location = new Location();
        location.setId(markerEntity.getContainerLocationId());
        savedMarker.setContainerLocation(location);
        location.setId(markerEntity.getReferencedLocationId());
        savedMarker.setReferencedLocation(location);
        return savedMarker;
    }

    @Override
    public Marker delete(Marker marker) {
        MarkerEntity markerEntity = MarkerMapper.mapper.toMarkerEntity(marker);
        markerDAO.delete(markerEntity);
        Marker deletedMarker = MarkerMapper.mapper.toMarker(markerEntity);
        Location location = new Location();
        location.setId(markerEntity.getContainerLocationId());
        deletedMarker.setContainerLocation(location);
        location.setId(markerEntity.getReferencedLocationId());
        deletedMarker.setReferencedLocation(location);
        return marker;
    }
    
}
