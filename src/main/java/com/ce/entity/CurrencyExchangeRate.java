package com.ce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "currency_exchange_rate")
public class CurrencyExchangeRate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "currency_code")
	private String currencyCode;

	@Column(name = "conversion_rate")
	private BigDecimal conversionRate;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "CurrencyExchangeRate [id=" + id + ", currencyCode=" + currencyCode + ", conversionRate="
				+ conversionRate + ", createdAt=" + createdAt + "]";
	}

}
