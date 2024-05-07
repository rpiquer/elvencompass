package com.elvencompass.maps.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.elvencompass.maps.common.dto.MarkerDTO;
import com.elvencompass.maps.controller.model.request.MarkerRequest;
import com.elvencompass.maps.controller.model.response.MarkerResponse;
import com.elvencompass.maps.domain.entity.Marker;
import com.elvencompass.maps.persistence.model.MarkerEntity;
import com.elvencompass.maps.persistence.model.SubMarkerEntity;

@Mapper(componentModel = "spring")
public interface MarkerMapper {
    MarkerMapper mapper = Mappers.getMapper(MarkerMapper.class);

    List<MarkerResponse> toMarkerResponseList(List<MarkerDTO> markerDTOList);

    @Mapping(target = "referencedLocationResponse", expression = "java(LocationMapper.mapper.toLocationResponse(markerDTO.getReferencedLocationDTO()))")
    @Mapping(target = "containerLocationResponse", expression = "java(LocationMapper.mapper.toLocationResponse(markerDTO.getContainerLocationDTO()))")
    MarkerResponse toMarkerResponse(MarkerDTO markerDTO);

    List<MarkerDTO> toMarkerDTOList(List<MarkerRequest> markerRequest);
    List<MarkerDTO> markerListToMarkerDTOList(List<Marker> marker);
    @Mapping(target = "referencedLocationDTO", expression = "java(LocationMapper.mapper.toLocationDTO(markerRequest.getReferencedLocationRequest()))")
    @Mapping(target = "containerLocationDTO", expression = "java(LocationMapper.mapper.toLocationDTO(markerRequest.getContainerLocationRequest()))")
    MarkerDTO toMarkerDTO(MarkerRequest markerRequest);
    @Mapping(target = "referencedLocationDTO", expression = "java(LocationMapper.mapper.toLocationDTO(marker.getReferencedLocation()))")
    @Mapping(target = "containerLocationDTO", expression = "java(LocationMapper.mapper.toLocationDTO(marker.getContainerLocation()))")
    MarkerDTO toMarkerDTO(Marker marker);

    List<Marker> toMarkerList(List<MarkerDTO> markerDTOList);

    @Mapping(target = "referencedLocation", expression = "java(LocationMapper.mapper.toLocation(subMarkerEntity.getReferencedSubLocationEntity()))")
    @Mapping(target = "containerLocation", expression = "java(LocationMapper.mapper.toLocation(subMarkerEntity.getContainerSubLocationEntity()))")
    Marker subMarkerEntityToMarker(SubMarkerEntity subMarkerEntity);
    @Mapping(target = "referencedLocation", expression = "java(LocationMapper.mapper.toLocation(markerDTO.getReferencedLocationDTO()))")
    @Mapping(target = "containerLocation", expression = "java(LocationMapper.mapper.toLocation(markerDTO.getContainerLocationDTO()))")
    Marker toMarker(MarkerDTO markerDTO);
    @Mapping(target = "referencedLocation", ignore = true)
    @Mapping(target = "containerLocation", ignore = true)
    Marker toMarker(MarkerEntity markerEntity);

    List<MarkerEntity> toMarkerEntityList(List<Marker> markerList);
    @Mapping(target = "referencedLocationId", ignore = true)
    @Mapping(target = "containerLocationId", ignore = true)
    MarkerEntity toMarkerEntity(Marker marker);

    List<SubMarkerEntity> toSubMarkerEntityList(List<Marker> markerList);
    @Mapping(target = "containerSubLocationEntity", expression = "java(LocationMapper.mapper.toSubLocationEntity(marker.getContainerLocation()))")
    @Mapping(target = "referencedSubLocationEntity", expression = "java(LocationMapper.mapper.toSubLocationEntity(marker.getReferencedLocation()))")
    SubMarkerEntity toSubMarkerEntity(Marker marker);
    
}
