package com.biricik.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biricik.demo.model.ApiResponse;

public interface ApiResponseRepository  extends JpaRepository<ApiResponse, Long>{
	Optional<ApiResponse> findByLongitudeAndLatitudeAndRadius(double longitude, double latitude, int radius);
}
