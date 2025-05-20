package com.ironhack.grades_data_service.repositories;

import com.ironhack.grades_data_service.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    /****
 * Retrieves all grades associated with a specific course code.
 *
 * @param courseCode the unique identifier of the course
 * @return a list of grades for the specified course code
 */
List<Grade> findByCourse_CourseCode(Long courseCode);

}
