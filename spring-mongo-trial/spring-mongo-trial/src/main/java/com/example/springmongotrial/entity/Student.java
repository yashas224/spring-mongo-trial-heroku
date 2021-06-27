package com.example.springmongotrial.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "student")
public class Student {
	@Id
	private String id;
	private String name;

	@Field(name = "mail")
	private String email;

	@DBRef(lazy = true)
	private Departmant department;
	
	@DBRef(lazy = true)
	private List<Subject> subjects;
	@Transient
	private double percetage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Departmant getDepartment() {
		return department;
	}

	public void setDepartment(Departmant department) {
		this.department = department;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Student() {
		super();
	}

	public Student(String id, String name, String email, Departmant department, List<Subject> subjects) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
		this.subjects = subjects;
	}

	public double getPercetage() {
		if (subjects != null) {
			int sum = subjects.stream().map(sub -> sub.getMarksObtained()).reduce(0, (res, n) -> res + n);
			System.out.println("--------------------------" + sum);
			this.percetage = (sum * 1.0) / subjects.size();
		}
		return this.percetage;
	}

}
