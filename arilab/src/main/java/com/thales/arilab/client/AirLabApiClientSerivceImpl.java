package com.thales.arilab.client;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirLabApiClientSerivceImpl implements AirLabApiClientSerivce {

	private final RestTemplate restTemplate;

	@Override
	public JsonNode fetchData(String url, String apiKey) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("api-key", apiKey);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		return Optional.of(
				restTemplate.exchange(
						url,
						HttpMethod.GET,
						entity,
						JsonNode.class)
				)
				.map(ResponseEntity::getBody)
				.orElse(JsonNodeFactory.instance.objectNode());
	}

}
