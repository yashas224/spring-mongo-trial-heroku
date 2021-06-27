package com.example.springmongotrial.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springmongotrial.entity.Subject;

@Repository
public interface SubjectRepositry extends MongoRepository<Subject, String> {

}
