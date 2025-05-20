package com.ironhack.student_catalog_service.client;

import com.ironhack.student_catalog_service.DTOS.CourseDTO;
import com.ironhack.student_catalog_service.DTOS.CourseGradeDTO;
import com.ironhack.student_catalog_service.DTOS.GradeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("grades-data-service")
public interface GradesDataServiceClient {

    /**
     * Retrieves course details for the specified course code from the grades data service.
     *
     * @param courseCode the unique code identifying the course
     * @return a CourseDTO containing information about the requested course
     */
    @GetMapping("/api/course/{courseCode}")
    CourseDTO getCourseByCode(@PathVariable Long courseCode);

    /**
     * Retrieves grade information for a specific course by its course code.
     *
     * @param courseCode the unique code identifying the course
     * @return a {@link CourseGradeDTO} containing grade details for the specified course
     */
    @GetMapping("/api/course/{courseCode}/grade")
    CourseGradeDTO getGradesByCourseCode(@PathVariable Long courseCode);

    /**
     * Retrieves a grade by its unique identifier from the grades data service.
     *
     * @param id the unique identifier of the grade to retrieve
     * @return a GradeDTO containing the details of the specified grade
     */
    @GetMapping("/api/grade/{id}")
    GradeDTO getGradeById(@PathVariable Long id);
}



