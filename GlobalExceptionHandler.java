package com.carservice.carExample.controller;

import java.util.Locale;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.carservice.carExample.dto.ErrorResponse;
import com.carservice.carExample.exception.RecordNotFoundException;
import com.carservice.carExample.exception.CarAlreadyExistsByVINNumberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String DEFAULT_ERROR_CODE = "error.runtime.default";
	private static final String ERROR_HEADER_MISSING_CODE = "error.header
	private static final String ERROR_INPUT_INVALID = "error.input.invalid";

	
	  private MessageSource msgSource;
	  
		@Autowired
		public GlobalExceptionHandler(MessageSource msgSource) {
			this.msgSource = msgSource;
		}
	 

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> processBindException(BindException ex, HttpServletRequest request) {
		return getErrorResponseResponseEntity(ex, request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> processMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		return getErrorResponseResponseEntity(ex, request);
	}
	/*
	 * private String localizedMessage(String code) { Locale currentLocale =
	 * LocaleContextHolder.getLocale(); if(code == null) { code =
	 * DEFAULT_ERROR_CODE; }
	 * 
	 * return messageSource.getMessage(code,null,currentLocale); }
	 */

	private void logException(HttpServletRequest request, Exception e, String code, String localizedMessage) {
		String sb = "EXCEPTION THROWN" + "--- REQUEST PATH: " + request.getRequestURL() + "--- EXCEPTION CODE:" + code
				+ "-- EXCEPTION MESSAGE: " + localizedMessage + "- EXCEPTION DETAILS: " + e.getMessage()
				+ "- EXCEPTION TYPE: " + e.getClass().getCanonicalName() + "--- STACK TRACE:";
		log.error(sb, e);

	}

	private String getDetailsFromBindingResult(BindingResult bindingResult) {
		return bindingResult.getAllErrors().stream().map(error -> {
			if (error instanceof FieldError) {
				return (((FieldError) error).getField()) + " " + error.getDefaultMessage();
			} else {
				return error.getDefaultMessage();
			}
		}).collect(Collectors.joining(","));
	}

	private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(BindException ex, HttpServletRequest request) {
		ErrorResponse errorResponseDto = new ErrorResponse();
		String code = ERROR_INPUT_INVALID;
		String message = "Input is invalid";
		errorResponseDto.setMessage(message);
		errorResponseDto.setDetails(getDetailsFromBindingResult(ex.getBindingResult()));
		errorResponseDto.setCode(code);
		logException(request, ex, code, message);
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> processUnhandledError(Exception e, HttpServletRequest request,
			HttpServletResponse response) {
		ErrorResponse errorMessage = new ErrorResponse();
		Locale currentLocale = LocaleContextHolder.getLocale();
		String code = DEFAULT_ERROR_CODE;
		String message = msgSource.getMessage(code,null,currentLocale);
		log.warn("An unexpected exception has occured.");
		logException(request, e, code, message);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setMessage(message);
		errorMessage.setCode(code);
		return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> processRecordNotFoundError(Exception e, HttpServletRequest request,
			HttpServletResponse response) {
		ErrorResponse errorMessage = new ErrorResponse();
		Locale currentLocale = LocaleContextHolder.getLocale();
		String code = ERROR_INPUT_INVALID;
		String message = msgSource.getMessage(code, null, currentLocale);
		logException(request, e, code, message);
		response.setStatus(HttpStatus.NOT_FOUND.value());
		errorMessage.setMessage(message);
		errorMessage.setCode(code);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CarAlreadyExistsByVINNumberException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> processCarAlreadyExistsByVINNumberException(
			CarAlreadyExistsByVINNumberException e, HttpServletRequest request, HttpServletResponse response) {
		ErrorResponse errorMessage = new ErrorResponse();
		String code = ERROR_INPUT_INVALID;
		String message = "Car already exists with the passed VIN number.";
		logException(request, e, code, message);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		errorMessage.setMessage(message);
		errorMessage.setCode(code);
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
