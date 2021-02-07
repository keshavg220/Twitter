package com.example.postgresdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.Question;
import com.example.postgresdemo.repository.QuestionRepository;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
    private QuestionRepository questionRepository;
	
	@PostMapping("/logins")
    public Question createLogin(@Valid @RequestBody Question login) {
        return questionRepository.save(login);
    }
}
