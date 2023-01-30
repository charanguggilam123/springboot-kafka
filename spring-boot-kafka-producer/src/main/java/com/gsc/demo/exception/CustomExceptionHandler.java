package com.gsc.demo.exception;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Set<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Set<String>> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();

			if (errors.containsKey(fieldName)) {
				errors.get(fieldName).add(error.getDefaultMessage());
			} else {
				Set<String> errorSet = new HashSet<>();
				errorSet.add(error.getDefaultMessage());
				errors.put(fieldName, errorSet);
			}
		});
		return errors;
	}

}
