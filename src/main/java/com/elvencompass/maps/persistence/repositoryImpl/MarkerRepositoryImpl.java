package com.elvencompass.maps.persistence.repositoryImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.elvencompass.maps.domain.entity.Marker;
import com.elvencompass.maps.domain.repository.MarkerRepository;
import com.elvencompass.maps.mapper.MarkerMapper;
import com.elvencompass.maps.persistence.dao.MarkerDAO;

@Repository
public class MarkerRepositoryImpl implements MarkerRepository{
    @Qualifier("MarkerDAO")

    private final MarkerDAO markerDAO;

    public MarkerRepositoryImpl(MarkerDAO markerDAO){
        this.markerDAO = markerDAO;
    }

    @Override
    public Optional<Marker> findById(int id) {
        return Optional.ofNullable(MarkerMapper.mapper.toMarker(markerDAO.findById(id).orElse(null)));
    }

    @Override
    public Marker save(Marker marker) {
        return MarkerMapper.mapper.toMarker(markerDAO.save(MarkerMapper.mapper.toMarkerEntity(marker)));
    }

    @Override
    public void delete(Marker marker) {
        markerDAO.delete(MarkerMapper.mapper.toMarkerEntity(marker));
    }
    
}
