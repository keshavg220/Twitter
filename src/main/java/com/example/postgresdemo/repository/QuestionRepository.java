package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Answer;
import com.example.postgresdemo.model.Question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
