package com.elvencompass.maps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.elvencompass.maps.common.dto.MarkerDTO;
import com.elvencompass.maps.controller.http_response.Response;
import com.elvencompass.maps.controller.model.request.MarkerRequest;
import com.elvencompass.maps.controller.model.response.MarkerResponse;
import com.elvencompass.maps.domain.service.MarkerService;
import com.elvencompass.maps.mapper.MarkerMapper;

@RequestMapping(MarkerController.MARKER)
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MarkerController {
    @Value("${application.url}")
    private String urlBase;

    public static final String MARKER = "/marker";

    @Autowired
    private MarkerService markerService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    public MarkerController(MarkerService markerService) {
        this.markerService = markerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MarkerRequest markerRequest) {
        MarkerDTO markerDTO = MarkerMapper.mapper.toMarkerDTO(markerRequest);

        MarkerResponse markerResponse = MarkerMapper.mapper.toMarkerResponse(markerService.create(markerDTO));
        return Response.builder().data(markerResponse).build();
        }
   
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") int id) {
        return Response.builder().data(MarkerMapper.mapper.toMarkerResponse(markerService.delete(id))).build();
    }
}
