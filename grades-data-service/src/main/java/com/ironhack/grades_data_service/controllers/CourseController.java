package com.ironhack.grades_data_service.controllers;

import com.ironhack.grades_data_service.DTOS.CourseGradeDTO;
import com.ironhack.grades_data_service.models.Course;
import com.ironhack.grades_data_service.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    /**
     * Retrieves a course by its code.
     *
     * @param courseCode the unique code identifying the course
     * @return a ResponseEntity containing the Course and HTTP status 200 (OK)
     */
    @GetMapping("/{courseCode}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseCode) {
        Course course = courseService.getCourseByCode(courseCode);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    /**
     * Retrieves grade information for a specific course by its code.
     *
     * @param courseCode the unique identifier of the course
     * @return a ResponseEntity containing the CourseGradeDTO for the specified course and HTTP status 200 (OK)
     */
    @GetMapping("/{courseCode}/grade")
    public ResponseEntity<CourseGradeDTO> getGradesForCourse(@PathVariable Long courseCode) {
        CourseGradeDTO grades = courseService.getCourseGrade(courseCode);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

}


