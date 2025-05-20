package com.ironhack.student_catalog_service.client;

import com.ironhack.student_catalog_service.DTOS.CourseDTO;
import com.ironhack.student_catalog_service.DTOS.CourseGradeDTO;
import com.ironhack.student_catalog_service.DTOS.GradeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("grades-data-service")
public interface GradesDataServiceClient {

    @GetMapping("/api/course/{courseCode}")
    CourseDTO getCourseByCode(@PathVariable Long courseCode);

    @GetMapping("/api/course/{courseCode}/grade")
    CourseGradeDTO getGradesByCourseCode(@PathVariable Long courseCode);

    @GetMapping("/api/grade/{id}")
    GradeDTO getGradeById(@PathVariable Long id);
}



