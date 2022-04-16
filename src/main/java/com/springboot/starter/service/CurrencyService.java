package com.springboot.starter.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.springboot.starter.model.dto.CurrencyDTO;

@Service
public interface CurrencyService {

	public CurrencyDTO getCurrencies() throws JsonMappingException, JsonProcessingException;
	
}
