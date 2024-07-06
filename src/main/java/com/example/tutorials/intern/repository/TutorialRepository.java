package com.example.tutorials.intern.repository;

import com.example.tutorials.intern.domain.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	List<Tutorial> findByPublished(boolean published);
	List<Tutorial> findByTitleContaining(String title);
	
	// JPA 문법으로 쿼리하기 어려운 경우에는 Query 어노테이션을 사용하여 직접 쿼리를 작성한다.
}
