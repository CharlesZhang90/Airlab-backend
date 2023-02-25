package com.thales.arilab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.thales.arilab.dto.SidsStarsDTO;
import com.thales.arilab.service.SidsStarsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SidsStarsController {


	private final SidsStarsService sidsStarsService;

	// Get All airports
	@CrossOrigin(origins = "*")
	@GetMapping("/airports")
	public ResponseEntity<JsonNode> getAllAirports() {
		return ResponseEntity.ok(sidsStarsService.getAllAirports());
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/{icao}")
	public ResponseEntity<SidsStarsDTO> getSidsByICAO(@PathVariable String icao, @RequestParam("sidsOrStars") String sidsOrStars) {
		JsonNode node = sidsStarsService.getSidsStarsByICAO(sidsOrStars, icao);
		return ResponseEntity.ok(sidsStarsService.getAirportsWithTopWayPoints(icao, node));
	}
}
