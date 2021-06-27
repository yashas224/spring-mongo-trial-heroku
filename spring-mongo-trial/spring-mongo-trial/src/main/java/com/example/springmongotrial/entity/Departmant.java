package com.example.springmongotrial.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "department")
public class Departmant {

	@Id
	private String id;

	@Field(name = "departmenr_name")
	private String departmentName;

	private String location;

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getLocation() {
		return location;
	}

	public Departmant() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Departmant(String id, String departmentName, String location) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.location = location;
	}

}
