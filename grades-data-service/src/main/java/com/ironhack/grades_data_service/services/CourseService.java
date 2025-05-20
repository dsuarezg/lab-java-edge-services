package com.ironhack.grades_data_service.services;

import com.ironhack.grades_data_service.DTOS.CourseGradeDTO;
import com.ironhack.grades_data_service.models.Course;
import com.ironhack.grades_data_service.models.Grade;
import com.ironhack.grades_data_service.repositories.CourseRepository;
import com.ironhack.grades_data_service.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;


    /**
     * Retrieves a course by its course code.
     *
     * @param courseCode the unique identifier of the course
     * @return the Course entity if found, or null if no course exists with the given code
     */
    public Course getCourseByCode(Long courseCode) {
        return courseRepository.findById(courseCode).orElse(null);
    }

    /**
     * Retrieves the course name and associated grades for a given course code.
     *
     * @param courseCode the unique identifier of the course
     * @return a CourseGradeDTO containing the course name and list of grades, or null if the course does not exist
     */
    public CourseGradeDTO getCourseGrade(Long courseCode) {
        Course course = getCourseByCode(courseCode);
        if (course == null){
            return null;
        }
        List<Grade> grades = gradeRepository.findByCourse_CourseCode(courseCode);
        return new CourseGradeDTO(course.getCourseName(), grades);
    }

}