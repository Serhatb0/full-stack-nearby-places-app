package com.biricik.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biricik.demo.model.ApiResponse;
import com.biricik.demo.service.PlacesService;

@RestController
@RequestMapping("/api/places")
@CrossOrigin
public class PlacesController {
	private final PlacesService placesService;

	public PlacesController(PlacesService placesService) {
		this.placesService = placesService;
	}

	@GetMapping
	public ApiResponse getNearbyPlaces(@RequestParam("longitude") double longitude,
			@RequestParam("latitude") double latitude, @RequestParam("radius") int radius) {
		return placesService.getNearbyPlaces(longitude, latitude, radius);

	}
}
