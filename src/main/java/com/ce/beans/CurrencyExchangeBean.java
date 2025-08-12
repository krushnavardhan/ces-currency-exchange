package com.ce.beans;

import java.math.BigDecimal;

public class CurrencyExchangeBean {

	private String baseCurrency;
	private String targetCurrency;
	private BigDecimal exchangeRate;
	private String rateDate;
	private String environment;

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

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getRateDate() {
		return rateDate;
	}

	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
