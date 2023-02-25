package com.thales.arilab.client;

import com.fasterxml.jackson.databind.JsonNode;

public interface AirLabApiClientSerivce {
	
	JsonNode fetchData(String url, String apiKey);
	
}
