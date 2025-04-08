package com.epam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz-app")
public class QuizController {

    @PostMapping("/quizzes")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<String> createQuiz(@RequestBody Test test){
        return ResponseEntity.ok().body("Quiz created successfully by admin");
    }

    @GetMapping("/quizzes/participate")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public ResponseEntity<String> participate(){
        return ResponseEntity.ok().body("Quiz has been successfully completed by user");
    }

}
