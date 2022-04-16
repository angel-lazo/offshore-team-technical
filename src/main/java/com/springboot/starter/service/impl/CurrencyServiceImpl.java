package com.springboot.starter.service.impl;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.starter.model.dto.CurrencyDTO;
import com.springboot.starter.model.dto.ExchangeRatesLatestDTO;
import com.springboot.starter.service.CurrencyService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class CurrencyServiceImpl implements CurrencyService{
	
    private static final Logger log = LogManager.getLogger(CurrencyServiceImpl.class);

	@Override
	public CurrencyDTO getCurrencies() throws JsonMappingException, JsonProcessingException {
		
		CurrencyDTO responseDto = new CurrencyDTO();
		String jsonData = "";
		
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				Request request = new Request.Builder()
				  .url("http://api.exchangeratesapi.io/v1/latest?access_key=3c75fafa174a777a4de251e0a77b24d6")
				  .method("GET", null)
				  .build();
				try {
					Response response = client.newCall(request).execute();
					jsonData = response.body().string();
				} catch (IOException e) {
					jsonData = "{\"success\":true,\"timestamp\":1650046924,\"base\":\"EUR\",\"date\":\"2022-04-15\",\"rates\":{\"USD\":1.080843,\"AUD\":1.46164,\"CAD\":1.362673,\"PLN\":4.63,\"MXN\":21.549736}}";
				}
			
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		ExchangeRatesLatestDTO exchangeRateDTO = om.readValue(jsonData, ExchangeRatesLatestDTO.class);
		
		for (HashMap<String, Double> i : exchangeRateDTO.getRates()) {
			responseDto.setCurrencyCodes(i.keySet());
		}
		
		return responseDto;
	}

}
