package com.springboot.starter.controllers.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.springboot.starter.model.dto.CurrencyDTO;
import com.springboot.starter.model.dto.ExchangeRequestDTO;
import com.springboot.starter.model.dto.ExchangeResponseDTO;
import com.springboot.starter.service.CurrencyService;
import com.springboot.starter.service.ExchangeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/v1/exchange")
@RestController
public class ExchangeRestController {
	
    @Autowired
    private ExchangeService exchangeService;

	@ApiOperation(value = "Get request to view exchange rates between two currencies",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 415, message = "Unsupported Media Type"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 503, message = "Service Unavailable")
	}
	)
    @GetMapping(value="rate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeResponseDTO getExchangeRate(@RequestBody ExchangeRequestDTO requestDto) throws JsonProcessingException {
        return exchangeService.getExchangeRate(requestDto);
    }
	
	@ApiOperation(value = "Post request that will create a transaction of buying currency",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 415, message = "Unsupported Media Type"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 503, message = "Service Unavailable")
	}
	)
    @PostMapping(value="buy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeResponseDTO buyCurrency(@RequestBody ExchangeRequestDTO requestDto) throws JsonProcessingException {
        return exchangeService.buyCurrency(requestDto);
    }
	
	@ApiOperation(value = "Post request that will create a transaction of selling currency",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 409, message = "Conflict"),
			@ApiResponse(code = 415, message = "Unsupported Media Type"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 503, message = "Service Unavailable")
	}
	)
    @PostMapping(value="sell", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeResponseDTO sellCurrency(@RequestBody ExchangeRequestDTO requestDto) throws JsonProcessingException {
        return exchangeService.sellCurrency(requestDto);
    }

}
