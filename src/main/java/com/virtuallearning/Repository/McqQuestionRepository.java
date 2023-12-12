package com.virtuallearning.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.virtuallearning.Entity.McqQuestion;

public interface McqQuestionRepository extends JpaRepository<McqQuestion, Long> {
	Page<McqQuestion> findAll(Pageable pageable);
}
