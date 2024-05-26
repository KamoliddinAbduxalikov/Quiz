package example.quiz.repository;

import example.quiz.domain.QuestionEntity;
import example.quiz.projection.question.QuestionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("SELECT q FROM QuestionEntity q WHERE q.quiz.id = :quizId")
    List<QuestionProjection> findQuestionsByQuizId(Long quizId);

    QuestionProjection findQuestionById(Long questionId);
}
