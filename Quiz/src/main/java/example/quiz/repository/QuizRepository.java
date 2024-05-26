package example.quiz.repository;

import example.quiz.domain.Quiz;
import example.quiz.projection.QuizProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT q FROM Quiz q")
    List<QuizProjection> findAllQuizzes();

    @Query("SELECT q FROM Quiz q WHERE q.id =: id")
    QuizProjection findQuizById(Long id);
}
