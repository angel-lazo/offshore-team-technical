package com.springboot.starter.controllers.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.springboot.starter.model.dto.CurrencyDTO;
import com.springboot.starter.service.CurrencyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/v1/currency")
@RestController
public class CurrencyRestController {
	
	private static final Logger log = LogManager.getLogger(CurrencyRestController.class);

    @Autowired
    private CurrencyService currencyService;

	@ApiOperation(value = "Get the list of currencies",response = Iterable.class)
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
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurrencyDTO getCurrencies() throws JsonMappingException, JsonProcessingException {
    	log.debug("REST request to get all currencies");
        return currencyService.getCurrencies();
    }

}
