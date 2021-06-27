package com.example.springmongotrial.repositry;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springmongotrial.entity.Departmant;
import com.example.springmongotrial.entity.Student;

@Repository
public interface StudentRepositry extends MongoRepository<Student, String> {

	public List<Student> findByName(String name);

	public List<Student> findByNameAndEmail(String name, String email);

	public List<Student> findByNameOrEmail(String name, String email);

	public List<Student> findByDepartmentDepartmentName(String deptName);

	public List<Student> findByEmail(Predicate<String> pred);

	public List<Student> findByEmailLike(String name);

	public List<Student> findByNameStartsWith(String name);

	public List<Student> findByDepartment(Departmant dObj);

	@Query(value = "{'name': ?0 }", fields = "{'?1': 1}")
	public List<Student> nativeQuery(String name, String projection);
}
