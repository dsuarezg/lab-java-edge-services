package com.ironhack.student_catalog_service.client;

import com.ironhack.student_catalog_service.DTOS.StudentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("student-info-service")
public interface StudentInfoServiceClient {
    @GetMapping("/api/student/{studentId}")
    StudentDTO getStudentId(@PathVariable Long studentId);
}
