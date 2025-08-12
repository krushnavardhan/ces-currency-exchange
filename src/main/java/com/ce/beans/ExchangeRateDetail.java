package com.ce.beans;

import java.math.BigDecimal;

public class ExchangeRateDetail {

	private BigDecimal exchangeRate;
	private String rateDate;

	public ExchangeRateDetail() {
		 
	}

	public ExchangeRateDetail(BigDecimal exchangeRate, String rateDate) {
		this.exchangeRate = exchangeRate;
		this.rateDate = rateDate;
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
}
