package com.students.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.students.db.entities.Students;
import com.students.db.repository.StudentsRepository;
import com.students.dto.StudentsDTO;
import com.students.utils.CommonConstants;
import com.students.utils.StudentsLogger;

@Service
@Scope
public class StudentsService {

	private static final String LOG_STR = "StudentsService.%s";

	@Autowired
	private StudentsLogger logger;

	@Autowired
	private StudentsRepository repository;

	public ResponseEntity<JsonNode> getAllStudents() {

		String methodName = "getAllStudents";

		logger.info(String.format(LOG_STR, methodName));

		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

		List<Students> studentsList = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));

		responseNode.put(CommonConstants.RESPONSE, CommonConstants.SUCCESS);

		ArrayNode dataArr = responseNode.putArray("data");

		studentsList.forEach(dataArr::addPOJO);

		logger.info(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

		return new ResponseEntity<>(responseNode, HttpStatus.OK);
	}

	public ResponseEntity<JsonNode> getStudent(@Positive(message = "Student id must be Positive value") Long id) {

		String methodName = "getStudent";

		logger.info(String.format(LOG_STR, methodName) + " id = " + id);

		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

		Optional<Students> student = repository.findById(id);

		if (student.isEmpty()) {

			responseNode.put(CommonConstants.RESPONSE, CommonConstants.ERROR);

			ArrayNode errorsArr = responseNode.putArray(CommonConstants.ERRORS);

			errorsArr.addObject().put(CommonConstants.ERRORCODE, "INVALID_DATA").put(CommonConstants.MESSAGE,
					"Student not available");

			logger.error(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

			return new ResponseEntity<>(responseNode, HttpStatus.NOT_FOUND);

		}

		responseNode.put(CommonConstants.RESPONSE, CommonConstants.SUCCESS);

		ArrayNode dataArr = responseNode.putArray("data");

		Arrays.asList(student.get()).forEach(dataArr::addPOJO);

		logger.info(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

		return new ResponseEntity<>(responseNode, HttpStatus.OK);
	}

	public ResponseEntity<JsonNode> addStudent(StudentsDTO studentData) {

		String methodName = "addStudent";

		logger.info(String.format(LOG_STR, methodName) + " studentData = " + studentData);

		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

		saveStudent(studentData, new Students());

		responseNode.put(CommonConstants.RESPONSE, CommonConstants.SUCCESS);

		logger.info(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

		return new ResponseEntity<>(responseNode, HttpStatus.OK);
	}

	public ResponseEntity<JsonNode> editStudent(StudentsDTO studentData) {

		String methodName = "editStudent";

		logger.info(String.format(LOG_STR, methodName) + " studentData = " + studentData);

		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

		Optional<Students> student = repository.findById(studentData.getId());

		if (student.isEmpty()) {

			responseNode.put(CommonConstants.RESPONSE, CommonConstants.ERROR);

			ArrayNode errorsArr = responseNode.putArray(CommonConstants.ERRORS);

			errorsArr.addObject().put(CommonConstants.ERRORCODE, "INVALID_DATA").put(CommonConstants.MESSAGE,
					"Student not available");

			logger.error(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

			return new ResponseEntity<>(responseNode, HttpStatus.NOT_FOUND);

		}

		saveStudent(studentData, student.get());

		responseNode.put(CommonConstants.RESPONSE, CommonConstants.SUCCESS);

		logger.info(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

		return new ResponseEntity<>(responseNode, HttpStatus.OK);
	}

	private void saveStudent(StudentsDTO studentData, Students student) {

		String methodName = "saveStudent";

		logger.info(String.format(LOG_STR, methodName) + " studentData = " + studentData);

		if (Objects.nonNull(studentData.getName()) && studentData.getName().isPresent())
			student.setName(studentData.getName().get());

		if (Objects.nonNull(studentData.getDateOfBirth()) && studentData.getDateOfBirth().isPresent())
			student.setDateOfBirth(studentData.getDateOfBirth().get());

		if (Objects.nonNull(studentData.getAddress()) && studentData.getAddress().isPresent())
			student.setAddress(studentData.getAddress().get());

		if (Objects.nonNull(studentData.getGender()) && studentData.getGender().isPresent())
			student.setGender(studentData.getGender().get());

		if (Objects.nonNull(studentData.getContactNumber()) && studentData.getContactNumber().isPresent())
			student.setContactNumber(studentData.getContactNumber().get());

		if (Objects.nonNull(studentData.getSports()))
			student.setSports(studentData.getSports());

		if (Objects.nonNull(studentData.getCurriculums()))
			student.setCurriculums(studentData.getCurriculums());

		repository.save(student);

	}

	public ResponseEntity<JsonNode> deleteStudents(Long[] ids) {

		String methodName = "deleteStudents";

		logger.info(String.format(LOG_STR, methodName) + " ids = " + Arrays.toString(ids));

		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

		Iterable<Long> studentIdItrs = Arrays.asList(ids);

		List<Students> studentList = repository.findAllById(studentIdItrs);

		List<Long> dbStudentIdList = studentList.stream().map(Students::getId).collect(Collectors.toList());

		List<Long> invalidIdList = Arrays.asList(ids).stream().filter(sId -> !dbStudentIdList.contains(sId))
				.collect(Collectors.toList());

		if (!invalidIdList.isEmpty()) {

			responseNode.put(CommonConstants.RESPONSE, CommonConstants.ERROR);

			ArrayNode errorsArr = responseNode.putArray(CommonConstants.ERRORS);
			
			invalidIdList.stream().forEach( sId -> {
				errorsArr.addObject().put(CommonConstants.ERRORCODE, "INVALID_DATA").put(CommonConstants.MESSAGE,
						"Invalid Student Id = "+sId);
			});

			logger.error(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

			return new ResponseEntity<>(responseNode, HttpStatus.NOT_FOUND);

		}
		
		repository.deleteInBatch(studentList);
		
		responseNode.put(CommonConstants.RESPONSE, CommonConstants.SUCCESS);

		logger.info(String.format(LOG_STR, methodName) + " , responseNode = " + responseNode);

		return new ResponseEntity<>(responseNode, HttpStatus.OK);
	}

}
