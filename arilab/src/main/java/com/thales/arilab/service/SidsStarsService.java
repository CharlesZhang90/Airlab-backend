package com.thales.arilab.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.thales.arilab.dto.SidsStarsDTO;

public interface SidsStarsService {

	public JsonNode getAllAirports();

	public JsonNode getSidsStarsByICAO(String sidsOrStars, String icao);

	public SidsStarsDTO getAirportsWithTopWayPoints(String icao, JsonNode sidsOrStars);

}
