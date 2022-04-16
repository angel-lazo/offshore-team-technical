package com.springboot.starter.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"currencyCodeInput", "amountInput","currencyCodeOutput", "amountOutput", "exchangeRate"})
public class ExchangeRequestDTO {

    public String currencyCodeInput;
    public Double amountInput;
    public String currencyCodeOutput;
    public Double amountOutput;
    
}
