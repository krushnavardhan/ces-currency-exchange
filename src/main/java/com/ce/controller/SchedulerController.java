package com.ce.controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ce.service.CurrencyExchangeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SchedulerController {

	@Autowired
	private CurrencyExchangeService ceService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${exchange.rate.api-token}")
	private String apiToken;

	@Value("${exchange.rate.api-endpoint.prefix}")
	private String urlPrefix;

	@Value("${exchange.rate.api-endpoint.suffix}")
	private String urlSuffix;

	public static final String SUCCESS = "success";

	private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Scheduled(cron = "0 0 16 * * *")
	// cron --> second minute hour dayOfMonth month dayOfWeek
	public void saveNewExchangeRates() throws IOException {
		log.info("Save latest exchange rates : {}", LocalTime.now());

		String url = urlPrefix + apiToken + urlSuffix;
		String response = restTemplate.getForObject(url, String.class);

		// Read and parse response using Jackson
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(response);
		JsonNode conversionRates = root.get("conversion_rates");

		String result = root.get("result").asText();
		if (SUCCESS.equalsIgnoreCase(result)) {
			String baseExchangeRate = root.get("base_code").asText();
			log.info("Base exchange rate : {}", baseExchangeRate);

			Map<String, Double> conversionRatesMap = objectMapper.convertValue(conversionRates,
					new TypeReference<Map<String, Double>>() {
					});
			ceService.saveNewExchangeRates(conversionRatesMap);
		}
	}

}
