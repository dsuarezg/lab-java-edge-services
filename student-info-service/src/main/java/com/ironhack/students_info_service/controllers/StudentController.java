package com.ironhack.students_info_service.controllers;

import com.ironhack.students_info_service.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Retrieves a student by their unique ID.
     *
     * @param studentId the ID of the student to retrieve
     * @return a ResponseEntity containing the student data if found, or an empty result if not
     */
    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<?> getStudentById(@PathVariable Long studentId){
        return (new ResponseEntity<>(studentRepository.findById(studentId), HttpStatus.OK));
    }


}
