package com.biricik.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.biricik.demo.model.ApiResponse;
import com.biricik.demo.repository.ApiResponseRepository;

@Service
public class PlacesService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiResponseRepository apiResponseRepository;

	public ApiResponse getNearbyPlaces(double longitude, double latitude, int radius) {
		Optional<ApiResponse> existingPlace = apiResponseRepository.findByLongitudeAndLatitudeAndRadius(longitude,
				latitude, radius);
		if (existingPlace.isPresent()) {
			return existingPlace.get();

		}
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + longitude + ","
				+ latitude + "&radius=" + radius + "&key=YOURAPİKEY";
		

		ResponseEntity<ApiResponse> response = restTemplate.getForEntity(url, ApiResponse.class);

		ApiResponse apiResponses = response.getBody();
		apiResponses.setLatitude(latitude);
		apiResponses.setLongitude(longitude);
		apiResponses.setRadius(radius);
		
		apiResponseRepository.save(apiResponses);

		return apiResponses;

	}


}