package com.ironhack.student_catalog_service.services;

import com.ironhack.student_catalog_service.DTOS.CourseDTO;
import com.ironhack.student_catalog_service.DTOS.CourseGradeDTO;
import com.ironhack.student_catalog_service.DTOS.GradeDTO;
import com.ironhack.student_catalog_service.DTOS.StudentDTO;
import com.ironhack.student_catalog_service.models.Catalog;
import com.ironhack.student_catalog_service.models.StudentGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * Retrieves a catalog of students and their grades for a given course code.
     *
     * Fetches course information and associated grades from external services. For each grade, retrieves the corresponding student details and compiles a list of student-grade entries. Returns a Catalog containing the course name and the list of student grades, or null if the course does not exist.
     *
     * @param courseCode the unique identifier of the course
     * @return a Catalog object with course name and student grades, or null if the course is not found
     */
    public Catalog getCatalogByCourseCode(Long courseCode) {
        CourseDTO course = restTemplate.getForObject(
                "http://grades-data-service/api/course/" + courseCode,
                CourseDTO.class
        );

        if (course == null) {
            return null;
        }
        CourseGradeDTO courseGrade = restTemplate.getForObject(
                "http://grades-data-service/api/course/" + courseCode + "/grade",
                CourseGradeDTO.class
        );

        List<StudentGrade> studentGrades = new ArrayList<>();

        if (courseGrade != null && courseGrade.getGrades() != null) {
            for (GradeDTO grade : courseGrade.getGrades()) {
                StudentDTO student = restTemplate.getForObject(
                        "http://student-info-service/api/student/" + grade.getStudentId(),
                        StudentDTO.class
                );

                if (student != null) {
                    studentGrades.add(new StudentGrade(
                            student.getStudentName(),
                            student.getStudentAge(),
                            grade.getGrade()
                    ));
                }
            }
        }

        return new Catalog(course.getCourseName(), studentGrades);
    }
}
