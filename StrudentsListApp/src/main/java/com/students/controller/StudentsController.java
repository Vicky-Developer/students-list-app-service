package com.students.controller;

import java.util.Arrays;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.students.dto.Create;
import com.students.dto.StudentsDTO;
import com.students.dto.Update;
import com.students.service.StudentsService;
import com.students.utils.CommonConstants;
import com.students.utils.StudentsLogger;

@RestController
@RequestMapping("/students")
//@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class StudentsController {

	private static final String LOG_STR = "StudentsController.%s";

	@Autowired
	private StudentsLogger logger;

	@Autowired
	private StudentsService service;

	@GetMapping
	public ResponseEntity<JsonNode> getAllStudents() {

		logger.info(String.format(LOG_STR, "getAllStudents"));

		return service.getAllStudents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<JsonNode> getStudents(
			@PathVariable(name = "id") @Positive(message = "Student id must be a Positive value") Long id) {

		logger.info(String.format(LOG_STR, "getStudents") + " , id = " + id);

		return service.getStudent(id);
	}

	@PostMapping
	public ResponseEntity<JsonNode> addStudent( @Validated(value = Create.class) @RequestBody(required = true) StudentsDTO studentData, BindingResult bindingResults) {

		logger.info(String.format(LOG_STR, "addStudent") + " , studentData = " + studentData);

		if (bindingResults.hasErrors()) {

			ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
			
			responseNode.put(CommonConstants.RESPONSE, CommonConstants.ERROR);

			ArrayNode errorsArr = responseNode.putArray("errors");

			for (ObjectError error : bindingResults.getAllErrors()) {

				errorsArr.addObject().put(CommonConstants.ERRORCODE, "INVALID_DATA").put(CommonConstants.MESSAGE,
						error.getDefaultMessage());

			}
			
			logger.error(String.format(LOG_STR, "addStudent") + " , responseNode = " + responseNode);

			return new ResponseEntity<>(responseNode, HttpStatus.BAD_REQUEST);

		}
		
		return service.addStudent(studentData);

	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<JsonNode> editStudent(@PathVariable(name = "id") @Positive(message = "Student id must be a Positive value") Long id, @Validated(value = Update.class) @RequestBody(required = true) StudentsDTO studentData, BindingResult bindingResults) {

		logger.info(String.format(LOG_STR, "editStudent") + " , studentData = " + studentData);

		if (bindingResults.hasErrors()) {

			ObjectNode responseNode = JsonNodeFactory.instance.objectNode();
			
			responseNode.put(CommonConstants.RESPONSE, CommonConstants.ERROR);

			ArrayNode errorsArr = responseNode.putArray("errors");

			for (ObjectError error : bindingResults.getAllErrors()) {

				errorsArr.addObject().put(CommonConstants.ERRORCODE, "INVALID_DATA").put(CommonConstants.MESSAGE,
						error.getDefaultMessage());

			}
			
			logger.error(String.format(LOG_STR, "editStudent") + " , responseNode = " + responseNode);

			return new ResponseEntity<>(responseNode, HttpStatus.BAD_REQUEST);

		}
		
		studentData.setId(id);
		
		return service.editStudent(studentData);

	}
	
	@DeleteMapping("/{ids}")
	public ResponseEntity<JsonNode> deleteStudents(
			@PathVariable(name = "ids") Long ...ids) {

		logger.info(String.format(LOG_STR, "getStudents") + " , ids = " + Arrays.toString(ids));

		return service.deleteStudents(ids);
	}

}
