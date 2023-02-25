package com.thales.arilab.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.thales.arilab.client.AirLabApiClientSerivce;
import com.thales.arilab.dto.SidsStarsDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SidsStarsServiceImpl implements SidsStarsService {

	@Value("${API_BASE_URL}")
	private String apiBaseUrl;

	@Value("${API_KEY}")
	private String apiKey;

	private final AirLabApiClientSerivce airLabApiClientSerivce;

	// Get all airports
	@Override
	public JsonNode getAllAirports() {
		String url = apiBaseUrl + "/airports";
		return airLabApiClientSerivce.fetchData(url, apiKey);
	}

	// Get sids / stars by ICAO
	@Override
	public JsonNode getSidsStarsByICAO(String sidsOrStars, String icao) {
		String url = apiBaseUrl + "/" + sidsOrStars + "/airport/" + icao;
		return airLabApiClientSerivce.fetchData(url, apiKey);
	}
	
	//get airports with top way points
	@Override
	public SidsStarsDTO getAirportsWithTopWayPoints(String icao, JsonNode sidsOrStars) {
		//initialize output dto
		SidsStarsDTO sidsStarsDto = new SidsStarsDTO();
		//initialize output dto's top way points
		List<Map.Entry<String, Integer>> list = new ArrayList<>();
		// sort way points
		List<Map.Entry<String, Integer>> sortedWaypoints = new ArrayList<>(getAllwaypointCount(sidsOrStars).entrySet());
		sortedWaypoints.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		//Get the top 2 ways points
		for (int i = 0; i < Math.min(2, sortedWaypoints.size()); i++) {
			list.add(sortedWaypoints.get(i));
		}
		sidsStarsDto.setIcao(icao);
		sidsStarsDto.setTopWaypoints(list);
		return sidsStarsDto;
	}

	// get all waypoints' names and counts
	private Map<String, Integer> getAllwaypointCount(JsonNode sidsOrStars) {
		Map<String, Integer> waypointCount = new HashMap<>();
		sidsOrStars.forEach(node -> {
			node.get("waypoints").forEach(subNode -> {
				String waypointName = subNode.get("name").asText();
				if(waypointCount.get(waypointName) != null) {
					waypointCount.put(subNode.get("name").asText(), waypointCount.get(waypointName) + 1);
				}else {
					waypointCount.put(subNode.get("name").asText(), 1);
				}
			});
		});
		return waypointCount;
	}

}
