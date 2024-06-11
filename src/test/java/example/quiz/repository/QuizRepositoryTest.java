package example.quiz.repository;

import example.quiz.domain.QuizEntity;
import example.quiz.projection.quiz.QuizProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @BeforeEach
    void setUp() {
        QuizEntity quiz1 = new QuizEntity();
        quiz1.setTitle("France");
        quizRepository.save(quiz1);

        QuizEntity quiz2 = new QuizEntity();
        quiz2.setTitle("Uzbekistan");
        quizRepository.save(quiz2);
    }

    @Test
    void findAllQuizzes() {
        List<QuizProjection> quizzes = quizRepository.findAllQuizzes();

        assertThat(quizzes).hasSize(2);
        assertThat(quizzes.get(0).getTitle()).isEqualTo("France");
        assertThat(quizzes.get(1).getTitle()).isEqualTo("Uzbekistan");
    }

    @Test
    void findQuizById() {
        List<QuizProjection> quizzes = quizRepository.findAllQuizzes();
        Long quizId = quizzes.get(0).getId();

        QuizProjection quiz = quizRepository.findQuizById(quizId);

        assertThat(quiz).isNotNull();
        assertThat(quiz.getTitle()).isEqualTo("France");
    }
}