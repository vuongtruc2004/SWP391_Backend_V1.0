package com.repository;

import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.repository.custom.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaSpecificationRepository<QuestionEntity, Long> {
    List<QuestionEntity> findByTitleContaining(String quesTitle);
}
