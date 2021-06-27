package com.example.springmongotrial.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongotrial.entity.Departmant;
import com.example.springmongotrial.entity.Student;
import com.example.springmongotrial.entity.Subject;
import com.example.springmongotrial.repositry.DepartmentRepositry;
import com.example.springmongotrial.repositry.StudentRepositry;
import com.example.springmongotrial.repositry.SubjectRepositry;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	StudentRepositry studentRepositry;

	@Autowired
	DepartmentRepositry departmentRepositry;

	@Autowired
	SubjectRepositry subjectrepositry;

	@PostMapping("/create")
	public Student create(@RequestBody Student student) {
		Departmant dObj = student.getDepartment();
		if (dObj != null) {
			departmentRepositry.save(dObj);
		}

		List<Subject> sObjs = student.getSubjects();
		if (sObjs != null && sObjs.size() > 0) {
			subjectrepositry.saveAll(sObjs);
		}
		Student obj = studentRepositry.save(student);
		return obj;
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Student> getById(@PathVariable(name = "id") String id) {
		Optional<Student> optional = studentRepositry.findById(id);
		if (optional.isPresent()) {
			return new ResponseEntity<Student>(optional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/get/all")
	public List<Student> getById() {
		System.out.println("Get all studnets called");
		List<Student> list = studentRepositry.findAll();
		// list.stream().forEach(s -> s.setPercetage());
		return list;
	}

	@PutMapping("/update")
	public Student update(@RequestBody Student student) {
		Student obj = studentRepositry.save(student);
		return obj;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") String id) {
		studentRepositry.deleteById(id);
	}

	@GetMapping("/getByName/{name}")
	public List<Student> getByName(@PathVariable(name = "name") String name) {
		return studentRepositry.findByName(name);
	}

	@GetMapping("/getByNameAndEmail")
	public List<Student> getByNameAndEmail(@RequestParam(name = "name") String name,
			@RequestParam(name = "email") String email) {
		return studentRepositry.findByNameAndEmail(name, email);
	}

	@GetMapping("/getByNameOrEmail")
	public List<Student> getByNameOrEmail(@RequestParam(name = "name") String name,
			@RequestParam(name = "email") String email) {
		return studentRepositry.findByNameOrEmail(name, email);
	}

	@GetMapping("/all/paginaion")
	public List<Student> pagination(@RequestParam(name = "size") int size, @RequestParam(name = "pageNo") int pageNo) {

		Pageable pageable = PageRequest.of(pageNo - 1, size);
		Page<Student> findAll = studentRepositry.findAll(pageable);
		return findAll.getContent();
		// return findAll.get().collect(Collectors.toList());
	}

	@GetMapping("/all/sort/{key}")
	public List<Student> pagination(@PathVariable(name = "key") String key) {
		Sort sort = Sort.by(Direction.ASC, key);
		return studentRepositry.findAll(sort);
	}

	@GetMapping("/findByDeptName/{deptName}")
	public List<Student> getByDeptName(@PathVariable(name = "deptName") String deptName) {
		return studentRepositry.findByDepartmentDepartmentName(deptName);
	}

	@GetMapping("/email/like/{name}")
	public List<Student> getEmailLike(@PathVariable(name = "name") String name) {
		return studentRepositry.findByEmailLike(name);
	}

	@GetMapping("/name/starts/{name}")
	public List<Student> getNameStartsike(@PathVariable(name = "name") String name) {
		return studentRepositry.findByNameStartsWith(name);
	}

	@GetMapping("/getBy/departmentName/{name}")
	public List<Student> getNameV2(@PathVariable(name = "name") String name) {
		Departmant dObj = departmentRepositry.findByDepartmentName(name);
		return studentRepositry.findByDepartment(dObj);
	}

	@GetMapping("/getBy/name-native/{name}/{projection}")
	public List<Student> getByNameQueryNative(@PathVariable(name = "name") String name,
			@PathVariable(name = "projection") String projection) {
		projection = projection.trim().isEmpty() ? "" : projection;
		return studentRepositry.nativeQuery(name, projection);
	}

}
