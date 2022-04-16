package com.springboot.starter.model.dto;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"success", "timestamp", "base", "date" ,"rates"})
public class ExchangeRatesLatestDTO {

    public boolean success;
    public int timestamp;
    public String base;
    public String date;
    public List<HashMap<String, Double>> rates;	
    
}
