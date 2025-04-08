package com.epam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz-app")
public class QuestionController {

    @PostMapping("/questions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> addQuestions(@RequestBody Test test){
        return ResponseEntity.ok().body("Questions created by admin successfully");
    }
}
