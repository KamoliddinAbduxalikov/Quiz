package example.quiz.service;

import example.quiz.domain.QuestionEntity;
import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.projection.question.QuestionProjection;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<QuestionProjection> getQuestionsByQuizId(Long quizId);

    void createQuestionSavedDTO(QuestionEntity question);

    void createQuestionCreatedDTO(QuestionCreationDTO question);

    void deleteQuestion(Long id);

    QuestionProjection getQuestionById(Long id);

    Optional<QuestionEntity> getQuestionEntityById(Long id);
}
