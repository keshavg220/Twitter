package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.example.postgresdemo.model.Answer;
import com.example.postgresdemo.model.FileUploadUtil;
import com.example.postgresdemo.model.Question;
import com.example.postgresdemo.repository.AnswerRepository;
import com.example.postgresdemo.repository.QuestionRepository;

import org.springframework.util.StringUtils;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public Page<Question> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }
    
    @GetMapping("/questions/{questionId}")
    public Optional<Question> getQuestionByQuestionId(@PathVariable Long questionId) {
        return questionRepository.findById(questionId);
    }

    
    @PostMapping("/questions")
    public Question createQuestion(@Valid @RequestBody Question question) {
        return questionRepository.save(question);
    }
    
    @PostMapping("/questions/save")
    public boolean saveUser(Question question,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        question.setPhotos(fileName);
         
        Question savedUser = questionRepository.save(question);
        System.out.println("saurabh");
        System.out.println(savedUser);
        System.out.println("id "+ savedUser.getId());

		String uploadDir = "user-photos/" + savedUser.getId();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return true;
    }

    @PostMapping("/logins")
    public Question createLogin(@Valid @RequestBody Question login) {
        return questionRepository.save(login);
    }

    @PutMapping("/questions/{questionId}")
    public Question updateQuestion(@PathVariable Long questionId,
                                   @Valid @RequestBody Question questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }


    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}