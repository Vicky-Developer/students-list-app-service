package com.students.execeptions_handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.fasterxml.jackson.databind.JsonNode;
import com.students.utils.StudentsLogger;

public class GlobalExeceptionHandler {

	@Autowired
	StudentsLogger logger;

	/*
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler({InternalServerException.class}) public
	 * ResponseEntity<JsonNode> handleInternalServer(Exception ex) {
	 * 
	 * }
	 */
}
