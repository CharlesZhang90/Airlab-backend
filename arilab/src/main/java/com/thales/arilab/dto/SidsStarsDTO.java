package com.thales.arilab.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SidsStarsDTO {

	private String icao;

	private List<Map.Entry<String, Integer>> topWaypoints;

}
