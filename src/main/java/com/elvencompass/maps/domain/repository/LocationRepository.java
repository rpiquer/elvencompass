package com.elvencompass.maps.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.elvencompass.maps.domain.entity.Location;

@Component
public interface LocationRepository {
    public List<Location> getAll();
    public Optional<Location> findByMapName(String mapName);
    public Optional<Location> findById(int id);
    public long getTotalNumberOfRecords();
    public Location save(Location location);
    public void delete(Location location);
}
