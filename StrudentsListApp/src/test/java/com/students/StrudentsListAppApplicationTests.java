package com.students;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.students.utils.StudentsLogger;

@SpringBootTest
@AutoConfigureMockMvc
class StrudentsListAppApplicationTests {
	
	private static final String LOG_STR = "StrudentsListAppApplicationTests.%s";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	StudentsLogger logger;

//	@Test
	public void getAllStudentsTest() throws Exception {
		
		this.mockMvc.perform(get("/students"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("response\":\"success")))
		.andExpect(content().string(containsString("data\":")));
	}

}
