package example.quiz.repository;

import example.quiz.domain.Question;
import example.quiz.projection.QuestionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.quiz.id = :quizId")
    List<QuestionProjection> findQuestionsByQuizId(Long quizId);
}
