package com.ironhack.student_catalog_service.services;

import com.ironhack.student_catalog_service.DTOS.CourseDTO;
import com.ironhack.student_catalog_service.DTOS.CourseGradeDTO;
import com.ironhack.student_catalog_service.DTOS.GradeDTO;
import com.ironhack.student_catalog_service.DTOS.StudentDTO;
import com.ironhack.student_catalog_service.client.GradesDataServiceClient;
import com.ironhack.student_catalog_service.client.StudentInfoServiceClient;
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
    private StudentInfoServiceClient studentInfoServiceClient;

    @Autowired
    private GradesDataServiceClient gradesDataServiceClient;

    /**
     * Builds a catalog of students and their grades for a given course code.
     *
     * Retrieves course details and associated student grades, assembling a catalog with the course name and a list of students and their grades. Returns {@code null} if the course does not exist.
     *
     * @param courseCode the unique identifier of the course
     * @return a {@link Catalog} containing the course name and student grades, or {@code null} if the course is not found
     */
    public Catalog getCatalogByCourseCode(Long courseCode) {
//        CourseDTO course = restTemplate.getForObject(
//                "http://grades-data-service/api/course/" + courseCode,
//                CourseDTO.class
//        );

        CourseDTO course = gradesDataServiceClient.getCourseByCode(courseCode);

        if (course == null) {
            return null;
        }
//        CourseGradeDTO courseGrade = restTemplate.getForObject(
//                "http://grades-data-service/api/course/" + courseCode + "/grade",
//                CourseGradeDTO.class
//        );

        CourseGradeDTO courseGrade = gradesDataServiceClient.getGradesByCourseCode(courseCode);

        List<StudentGrade> studentGrades = new ArrayList<>();

        if (courseGrade != null && courseGrade.getGrades() != null) {
            for (GradeDTO grade : courseGrade.getGrades()) {
//                StudentDTO student = restTemplate.getForObject(
//                        "http://student-info-service/api/student/" + grade.getStudentId(),
//                        StudentDTO.class
//                );

                StudentDTO student = studentInfoServiceClient.getStudentId(grade.getStudentId());


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
