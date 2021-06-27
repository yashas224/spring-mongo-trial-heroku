package com.example.springmongotrial.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springmongotrial.entity.Departmant;

@Repository
public interface DepartmentRepositry extends MongoRepository<Departmant, String> {

	Departmant findByDepartmentName(String name);

}
