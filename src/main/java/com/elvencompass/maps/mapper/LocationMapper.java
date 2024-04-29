package com.elvencompass.maps.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.elvencompass.maps.common.dto.LocationDTO;
import com.elvencompass.maps.controller.model.request.LocationRequest;
import com.elvencompass.maps.controller.model.response.LocationResponse;
import com.elvencompass.maps.domain.entity.Location;
import com.elvencompass.maps.domain.entity.Marker;
import com.elvencompass.maps.persistence.model.LocationEntity;
import com.elvencompass.maps.persistence.model.SubLocationEntity;
import com.elvencompass.maps.persistence.model.SubMarkerEntity;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationMapper mapper = Mappers.getMapper(LocationMapper.class);

    List<LocationResponse> toLocationResponseList(List<LocationDTO> locationDTOList);
    @Mapping(target = "markerResponseList", expression = "java(MarkerMapper.mapper.toMarkerResponseList(locationDTO.getMarkerDTOList()))")
    LocationResponse toLocationResponse(LocationDTO locationDTO);

    List<LocationDTO> toLocationDTOList(List<Location> locationList);
    @Mapping(target = "markerDTOList", expression = "java(MarkerMapper.mapper.toMarkerDTOList(locationRequest.getMarkerRequestList()))")
    LocationDTO toLocationDTO(LocationRequest locationRequest);
    @Mapping(target = "markerDTOList", expression = "java(MarkerMapper.mapper.markerListToMarkerDTOList(location.getMarkerList()))")
    LocationDTO toLocationDTO(Location location);

    List<Location> toLocationList(List<LocationEntity> locationEntityList);
    @Mapping(target = "markerList", expression = "java(MarkerMapper.mapper.toMarkerList(locationDTO.getMarkerDTOList()))")
    Location toLocation(LocationDTO locationDTO);
    @Mapping(target = "markerList", expression = "java(mapSubMarkerEntityListToMarkerList(locationEntity.getSubMarkerEntityList()))")
    Location toLocation(LocationEntity locationEntity);
    @Mapping(target = "markerList", ignore = true)
    Location toLocation(SubLocationEntity subLocationEntity);

    @Named("SubMarkerEntityListToMarkerList")
    default List<Marker> mapSubMarkerEntityListToMarkerList(List<SubMarkerEntity> subMarkerEntityList){
        List<Marker> markerList = new ArrayList<>();
        for (SubMarkerEntity subMarkerEntity : subMarkerEntityList) {
            markerList.add(MarkerMapper.mapper.subMarkerEntityToMarker(subMarkerEntity));
        }
        return markerList;
    }


    @Mapping(target = "subMarkerEntityList", expression = "java(MarkerMapper.mapper.toSubMarkerEntityList(location.getMarkerList()))")
    LocationEntity toLocationEntity(Location location);
    SubLocationEntity toSubLocationEntity(Location location);
    
    @Mapping(target = "markerList", ignore = true)
    void updateLocationFromLocationDTO(LocationDTO locationDTO, @MappingTarget Location location);
}
