package com.virtuallearning.Repository;

//StudentRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtuallearning.Entity.Students;

public interface StudentRepository extends JpaRepository<Students, Long> {
	// You can add custom queries if needed
	Students findStudentByEmailAndPassword(String email, String password);

	@Query("SELECT COUNT(s) FROM Students s WHERE s.email = :email")
	int countByEmail(@Param("email") String email);

	@Query("SELECT COUNT(s) FROM Students s WHERE s.phNo = :phNo")
	int countByPhNo(@Param("phNo") long phNo);
}
