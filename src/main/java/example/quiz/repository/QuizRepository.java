package example.quiz.repository;

import example.quiz.domain.QuizEntity;
import example.quiz.projection.quiz.QuizProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    @Query("SELECT q FROM QuizEntity q")
    List<QuizProjection> findAllQuizzes();

    @Query("SELECT q FROM QuizEntity q WHERE q.id =: id")
    QuizProjection findQuizById(Long id);
}
