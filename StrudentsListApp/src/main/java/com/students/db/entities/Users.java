package com.students.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Users {

	@Id
	@NotNull(message="id must not be null")
	@Column(name = "id", columnDefinition = "default nextval('users_seq'::regclass)")
	private Integer id;

	@NotNull(message="name must not be null")
	@Column(name = "name")
	private String name;
	
	@NotNull(message="userName must not be null")
	@Column(name = "user_name")
	private String userName;

	@NotNull(message="password must not be null")
	@Column(name = "password")
	private String password;

	@Column(name = "is_active", columnDefinition = "default false")
	private Boolean isActive;

	@Column(name = "is_session_expired", columnDefinition = "default true")
	private Boolean isSessionExpired;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsSessionExpired() {
		return isSessionExpired;
	}

	public void setIsSessionExpired(Boolean isSessionExpired) {
		this.isSessionExpired = isSessionExpired;
	}

	

}
