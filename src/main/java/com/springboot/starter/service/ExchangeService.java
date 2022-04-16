package com.springboot.starter.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.springboot.starter.model.dto.ExchangeRequestDTO;
import com.springboot.starter.model.dto.ExchangeResponseDTO;

@Service
public interface ExchangeService {

	public ExchangeResponseDTO getExchangeRate(ExchangeRequestDTO requestDto) throws JsonMappingException, JsonProcessingException;
	
	public ExchangeResponseDTO buyCurrency(ExchangeRequestDTO requestDto) throws JsonMappingException, JsonProcessingException;
	
	public ExchangeResponseDTO sellCurrency(ExchangeRequestDTO requestDto) throws JsonMappingException, JsonProcessingException;
	
}
