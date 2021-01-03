package com.students;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.students.utils.CommonConstants;
import com.students.utils.StudentsLogger;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsControllerTest {

	private static final String LOG_STR = "StudentsControllerTest.%s";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentsLogger logger;

//	@Test
	@Order(1)
	public void getAllStudentsTest() throws Exception {

		logger.info(String.format(LOG_STR, "getAllStudentsTest"));

		this.mockMvc.perform(get("/students")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("response\":\"success")))
				.andExpect(content().string(containsString("data\":")));
	}
	
//	@Test
	@Order(2)
	public void getStudentTest() throws Exception {

		logger.info(String.format(LOG_STR, "getStudentTest"));

		this.mockMvc.perform(get("/students/1001")).andDo(print()).andExpect(status().isOk());

	}

//	@Test
	@Order(3)
	public void getStudentTestWithInvalidId() throws Exception {

		logger.info(String.format(LOG_STR, "getStudentTestWithInvalidId"));

		ResultActions resultActions = this.mockMvc.perform(get("/students/1")).andDo(print()).andExpect(status().isNotFound());

		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode resposeNode = mapper.readTree(contentAsString);
		
		assertTrue(CommonConstants.ERROR.equalsIgnoreCase(resposeNode.get(CommonConstants.RESPONSE).asText()));
		
		assertTrue(resposeNode.has(CommonConstants.ERRORS));
		
		assertTrue(resposeNode.get(CommonConstants.ERRORS).has(0));
		
		assertTrue(resposeNode.get(CommonConstants.ERRORS).get(0).has(CommonConstants.ERRORCODE));

		assertTrue(CommonConstants.INVALID_DATA.equalsIgnoreCase(resposeNode.get(CommonConstants.ERRORS).get(0).get(CommonConstants.ERRORCODE).asText()));

		assertTrue(resposeNode.get(CommonConstants.ERRORS).get(0).has(CommonConstants.MESSAGE));

		assertTrue("Student not available".equalsIgnoreCase(resposeNode.get(CommonConstants.ERRORS).get(0).get(CommonConstants.MESSAGE).asText()));
	}
	
//	@Test
	@Order(4)
	public void addStudentTest()  throws Exception {
		
		logger.info(String.format(LOG_STR, "addStudentTest"));
		
		ResultActions resultActions = this.mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content("{ \"name\": \"Steve\", \"dateOfBirth\": \"1994-08-02\", \"address\": \"vicky addr\", \"gender\": \"male\", \"contactNumber\": 90431900353, \"sports\": \"abc\", \"curriculums\": \"abc\" }")).andDo(print()).andExpect(status().isOk());
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		
		logger.info(String.format(LOG_STR, "addStudentTest")+" , add-contentAsString = "+contentAsString);
	}
	
//	@Test
	@Order(5)
	public void editStudentTest()  throws Exception {
		
		logger.info(String.format(LOG_STR, "editStudentTest"));
		
		ResultActions resultActions = this.mockMvc.perform(put("/students/1001").contentType(MediaType.APPLICATION_JSON).content("{ \"name\": \"Steve\", \"dateOfBirth\": \"1994-08-02\", \"address\": \"vicky addr\", \"gender\": \"male\", \"contactNumber\": 90431900353, \"sports\": \"abc\", \"curriculums\": \"abc\" }")).andDo(print()).andExpect(status().isOk());
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		
		logger.info(String.format(LOG_STR, "editStudentTest")+" , edit-contentAsString = "+contentAsString);
	}
	
//	@Test
	@Order(6)
	public void deleteStudentTest()  throws Exception {
		
		logger.info(String.format(LOG_STR, "deleteStudentTest"));
		
		ResultActions resultActions = this.mockMvc.perform(delete("/students/1020")).andDo(print()).andExpect(status().isOk());
		
		MvcResult result = resultActions.andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		
		logger.info(String.format(LOG_STR, "deleteStudentTest")+" , delete-contentAsString = "+contentAsString);
	}
	

}
