package com.ce.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ce.beans.CurrencyExchangeBean;
import com.ce.beans.CurrenyExchangeHistory;
import com.ce.beans.ExchangeRateDetail;
import com.ce.entity.CurrencyExchangeRate;
import com.ce.repository.CurrencyExchangeRateRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRateRepository cerRepository;

	private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeServiceImpl.class);

	@Override
	public CurrencyExchangeBean findCurrencyExchangeBetweenFromAndTo(String from, String to) {

		log.info("Get curreny exchange rate from : {} to {}", from, to);
		CurrencyExchangeRate cerFrom = cerRepository.findTop1ByCurrencyCodeOrderByCreatedAtDesc(from);
		CurrencyExchangeRate cerTo = cerRepository.findTop1ByCurrencyCodeOrderByCreatedAtDesc(to);

		if (cerFrom == null || cerTo == null) {
			// Currency not found in DB
			return null;
		}
		log.info("curreny exchange rate for : {} is :{}", from, cerFrom.getConversionRate());
		log.info("curreny exchange rate for : {} is :{}", to, cerTo.getConversionRate());

		BigDecimal conversionRate = cerTo.getConversionRate().divide(cerFrom.getConversionRate()).setScale(4, RoundingMode.HALF_UP);
		log.info("curreny exchange rate for : {} to {} is : {}", from, to, conversionRate);

		CurrencyExchangeBean bean = new CurrencyExchangeBean();
		bean.setBaseCurrency(from);
		bean.setTargetCurrency(to);
		bean.setExchangeRate(conversionRate);
		bean.setRateDate(cerFrom.getCreatedAt().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		return bean;
	}

	@Override
	public void saveNewExchangeRates(Map<String, Double> conversionRatesMap) {
		log.info("Saving exchange rates...");
		for (Map.Entry<String, Double> entry : conversionRatesMap.entrySet()) {
			String currencyCode = entry.getKey();
			double conversionRate = entry.getValue();

			CurrencyExchangeRate rate = new CurrencyExchangeRate();
			rate.setCurrencyCode(currencyCode);
			rate.setConversionRate(BigDecimal.valueOf(conversionRate));
			rate.setCreatedAt(LocalDateTime.now());
			cerRepository.save(rate);
		}
		log.info("Saved {} exchange rates to DB", conversionRatesMap.size());
	}

	@Override
	public CurrenyExchangeHistory findCurrencyExchangeHistoryBetweenFromAndTo(String from, String to) {

		log.info("Get curreny exchange rate from : {} to {}", from, to);
		List<CurrencyExchangeRate> cerFromList = cerRepository.findTop30ByCurrencyCodeOrderByCreatedAtDesc(from);
		List<CurrencyExchangeRate> cerToList = cerRepository.findTop30ByCurrencyCodeOrderByCreatedAtDesc(to);

		if (cerFromList.isEmpty() || cerToList.isEmpty()) {
			// Currency not found in DB
			return null;
		}
		
		CurrenyExchangeHistory ceh = new CurrenyExchangeHistory();
		List<ExchangeRateDetail> erdList = new ArrayList<>();
		ceh.setBaseCurrency(from);
		ceh.setTargetCurrency(to);
		Map<String, CurrencyExchangeRate> cerToMap = cerToList.stream().collect(
				Collectors.toMap(e -> e.getCreatedAt().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), e -> e));

		for (CurrencyExchangeRate cerFrom : cerFromList) {
			String cerCreatedAtStr = cerFrom.getCreatedAt().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			if (cerToMap.containsKey(cerCreatedAtStr)) {
				CurrencyExchangeRate cerTo = cerToMap.get(cerCreatedAtStr);
				BigDecimal conversionRate = cerTo.getConversionRate().divide(cerFrom.getConversionRate()).setScale(4, RoundingMode.HALF_UP);
				erdList.add(new ExchangeRateDetail(conversionRate, cerCreatedAtStr));
				log.info("curreny exchange rate for : {} to {} is : {}", from, to, conversionRate);
			}
		}
		ceh.setExchangeRateDetails(erdList);
		return ceh;
	}

}
