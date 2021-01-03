package com.students.dto;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentsDTO {

	private Long id;

	private Optional<@NotNull(message="name must not be null",groups = {Create.class,Update.class})
	@NotBlank(message="name must not be blank",groups ={Create.class,Update.class}) String> name;

	private Optional<@NotNull(message="dateOfBirth must not be null",groups = {Create.class,Update.class}) LocalDate> dateOfBirth;

	private Optional<@NotNull(message="address must not be null",groups = {Create.class,Update.class}) String> address;

	private Optional<@NotNull(message="gender must not be null",groups = {Create.class,Update.class}) String> gender;
	
	private Optional<@NotNull(message="contactNumber must not be null",groups = {Create.class,Update.class}) Long> contactNumber;
	
	private String sports;

	private String curriculums;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Optional<String> getName() {
		return name;
	}

	public void setName(Optional<String> name) {
		this.name = name;
	}



	public Optional<LocalDate> getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Optional<LocalDate> dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Optional<String> getAddress() {
		return address;
	}

	public void setAddress(Optional<String> address) {
		this.address = address;
	}

	public Optional<String> getGender() {
		return gender;
	}

	public void setGender(Optional<String> gender) {
		this.gender = gender;
	}

	public Optional<Long> getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Optional<Long> contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(String curriculums) {
		this.curriculums = curriculums;
	}

	@Override
	public String toString() {
		return "StudentsDTO [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", address=" + address
				+ ", gender=" + gender + ", contactNumber=" + contactNumber + ", sports=" + sports + ", curriculums="
				+ curriculums + "]";
	}


	
	
}
