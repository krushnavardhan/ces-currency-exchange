package com.ce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ce.entity.CurrencyExchangeRate;

public interface CurrencyExchangeRateRepository extends JpaRepository<CurrencyExchangeRate, Integer> {

	CurrencyExchangeRate findTop1ByCurrencyCodeOrderByCreatedAtDesc(String currenyCode);
	
	List<CurrencyExchangeRate> findTop30ByCurrencyCodeOrderByCreatedAtDesc(String currenyCode);

}
