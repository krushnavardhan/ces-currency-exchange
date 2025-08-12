package com.ce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ce.beans.CurrencyExchangeBean;
import com.ce.beans.CurrenyExchangeHistory;
import com.ce.beans.ErrorResponse;
import com.ce.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private CurrencyExchangeService ceService;

	@Autowired
	private Environment environment;

	private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<?> getCurrencyExchangeRatesFromAndTo(@PathVariable("from") String from,
			@PathVariable("to") String to) {

		log.info("Get curreny exchange rate from : {} to {}", from, to);
		CurrencyExchangeBean currencyExchange = ceService.findCurrencyExchangeBetweenFromAndTo(from, to);
		if (currencyExchange == null) {
			ErrorResponse errorBean = new ErrorResponse("CURRENCY_NOT_FOUND", "No currency exchange data found between " + from + " and " + to);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBean);
		}
		currencyExchange.setEnvironment(environment.getProperty("server.port"));
		return ResponseEntity.ok(currencyExchange);
	}
	
	@GetMapping("/currency-exchange/history/from/{from}/to/{to}")
	public ResponseEntity<?> getCurrencyExchangeHistoryFromAndTo(@PathVariable("from") String from,
			@PathVariable("to") String to) {

		log.info("Get curreny exchange history from : {} to {}", from, to);
		CurrenyExchangeHistory currencyExchangeHistory = ceService.findCurrencyExchangeHistoryBetweenFromAndTo(from, to);
		if (currencyExchangeHistory == null) {
			ErrorResponse errorBean = new ErrorResponse("CURRENCY_NOT_FOUND", "No currency exchange data found between " + from + " and " + to);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBean);
		}
		return ResponseEntity.ok(currencyExchangeHistory);
	}

}
