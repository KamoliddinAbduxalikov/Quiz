package example.quiz.service;

import example.quiz.dto.quiz.QuizCreationDTO;
import example.quiz.projection.quiz.QuizProjection;

import java.util.List;

public interface QuizService {

    List<QuizProjection> getAllQuizzes();

    QuizProjection getQuizById(Long id);

    void createQuiz(QuizCreationDTO quiz);

    void deleteQuiz(Long id);
}
