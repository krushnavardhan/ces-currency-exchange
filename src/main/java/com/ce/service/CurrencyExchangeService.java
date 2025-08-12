package com.ce.service;

import java.util.Map;

import com.ce.beans.CurrencyExchangeBean;
import com.ce.beans.CurrenyExchangeHistory;

public interface CurrencyExchangeService {

	CurrencyExchangeBean findCurrencyExchangeBetweenFromAndTo(String from, String to);

	void saveNewExchangeRates(Map<String, Double> conversionRatesMap);

	CurrenyExchangeHistory findCurrencyExchangeHistoryBetweenFromAndTo(String from, String to);

}
