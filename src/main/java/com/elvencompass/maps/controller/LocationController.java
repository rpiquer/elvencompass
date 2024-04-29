package com.elvencompass.maps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elvencompass.maps.common.dto.LocationDTO;
import com.elvencompass.maps.controller.http_response.Response;
import com.elvencompass.maps.controller.model.request.LocationRequest;
import com.elvencompass.maps.controller.model.response.LocationResponse;
import com.elvencompass.maps.domain.service.LocationService;
import com.elvencompass.maps.mapper.LocationMapper;

@RequestMapping(LocationController.LOCATION)
@RestController
public class LocationController {
    @Value("${application.url}")
    private String urlBase;

    public static final String LOCATION = "/maps";

    @Autowired
    private LocationService locationService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response findAll() {
        List<LocationResponse> locationResponseList = LocationMapper.mapper.toLocationResponseList(locationService.getAll());
        long totalRecords = locationService.getTotalNumberOfRecords();
        return Response.builder().data(locationResponseList).totalRecords(totalRecords).build();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{mapName}")
    public Response find(@PathVariable("mapName") String mapName) {
        LocationResponse locationResponse = LocationMapper.mapper.toLocationResponse(locationService.findByMapName(mapName));
        return Response.builder().data(locationResponse).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody LocationRequest locationRequest) {
        LocationDTO locationDTO = LocationMapper.mapper.toLocationDTO(locationRequest);
        LocationResponse locationResponse = LocationMapper.mapper.toLocationResponse(locationService.create(locationDTO));
        return Response.builder().data(locationResponse).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody LocationRequest locationRequest) {
        LocationDTO locationDTO = LocationMapper.mapper.toLocationDTO(locationRequest);
        locationDTO.setId(id);
        LocationResponse locationResponse = LocationMapper.mapper.toLocationResponse(locationService.update(locationDTO));
        return Response.builder().data(locationResponse).build();
    }
   
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        locationService.delete(id);
    }

}
