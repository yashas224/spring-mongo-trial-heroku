package com.example.springmongotrial.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "subject")
public class Subject {
	@Id
	private String id;

	@Field(name = "subject_name")
	private String subjectName;

	@Field(name = "marks_obtained")
	private int marksObtained;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	public Subject() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Subject(String id, String subjectName, int marksObtained) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.marksObtained = marksObtained;
	}

}
