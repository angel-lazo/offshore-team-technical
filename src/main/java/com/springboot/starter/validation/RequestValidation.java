package com.springboot.starter.validation;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import com.springboot.starter.exception.InvalidRequestException;

@Component
public class RequestValidation<T> {
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	public void validate(T baseDTO) {
		
		Set<ConstraintViolation<T>> violations = validator.validate(baseDTO);
		
		if(!violations.isEmpty()) {
			throw new InvalidRequestException(interpretViolation(violations));
		}
		
	}

	private String interpretViolation(Set<ConstraintViolation<T>> violations) {
		return violations.stream().map(ConstraintViolation::getMessage).sorted().collect(Collectors.joining(", "));	
	}

}
