package com.students.db.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(allocationSize = 1,name = "students_seq",sequenceName = "students_seq")
public class Students {
	
	@Id
	@NotNull(message="id must not be null")
	@Column(name = "id", columnDefinition = "default nextval('students_seq'::regclass)")
	@GeneratedValue(generator = "students_seq",strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotNull(message="name must not be null")
	@Column(name = "name")
	private String name;

	@NotNull(message="datOfBirth must not be null")
	@Column(name = "date_of_birth")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private LocalDate dateOfBirth;

	@NotNull(message="address must not be null")
	@Column(name = "address")
	private String address;

	@NotNull(message="gender must not be null")
	@Column(name = "gender")
	private String gender;

	@NotNull(message="contactNumber must not be null")
	@Column(name = "contact_number")
	private Long contactNumber;

	@Column(name = "sports")
	private String sports;

	@Column(name = "curriculums")
	private String curriculums;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
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
		return "Students [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", address=" + address
				+ ", gender=" + gender + ", contactNumber=" + contactNumber + ", sports=" + sports + ", curriculums="
				+ curriculums + "]";
	}


	


}
