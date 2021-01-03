package com.students.dto.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.students.db.entities.Students;
import com.students.dto.StudentsDTO;
import com.students.utils.StudentsLogger;

@Component
public class StudentsConverter implements Converter<Students, StudentsDTO>{
	
	private static final String LOG_STR = "StudentsConverter.%s";
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StudentsLogger logger;
	

	@Override
	public StudentsDTO convert(Students entityObj) {
		
		logger.info(String.format(LOG_STR, "convert"));
		
		StudentsDTO  dtoObj = new StudentsDTO();
		
		modelMapper.map(entityObj, dtoObj);
		
		return dtoObj;
	}



}
