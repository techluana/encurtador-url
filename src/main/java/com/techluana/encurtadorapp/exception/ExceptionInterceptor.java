package com.techluana.encurtadorapp.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor {

	@ExceptionHandler(value = { TransactionSystemException.class })
	public ResponseEntity<Object> constraintViolationException(TransactionSystemException ex) {
		if(ex.getCause().getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException cve = (ConstraintViolationException) ex.getCause().getCause();
			Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
			StringBuilder mensagens = new StringBuilder();
			constraintViolations.stream().forEach(e -> mensagens.append(e.getMessage()).append(". "));
			return new ResponseEntity<>(mensagens, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
