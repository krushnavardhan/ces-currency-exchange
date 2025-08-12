package com.ce.beans;

import java.util.List;

public class CurrenyExchangeHistory {

	private String baseCurrency;
	private String targetCurrency;
	private List<ExchangeRateDetail> exchangeRateDetails;

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public List<ExchangeRateDetail> getExchangeRateDetails() {
		return exchangeRateDetails;
	}

	public void setExchangeRateDetails(List<ExchangeRateDetail> exchangeRateDetails) {
		this.exchangeRateDetails = exchangeRateDetails;
	}

}
