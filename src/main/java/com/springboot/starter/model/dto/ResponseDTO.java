package com.springboot.starter.model.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    private Boolean success;
    private String errorCode;
    private String errorMessage;
    private T object;
    
	@Override
	public String toString() {
		try {
			return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT).writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "Unable to parse the object";
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> updateJsonFields(Map<String, String> values){
		ObjectMapper oMapper = new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		oMapper.registerModule(new JavaTimeModule());
		Map<String, Object> map = oMapper.convertValue(this, Map.class);

		for (Map.Entry<String, String> value : values.entrySet()) {
			Object obj = map.remove(value.getKey());
			map.put(value.getValue(), obj);
		}

	    return map;

	}

}
