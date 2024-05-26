package example.quiz.service;

import example.quiz.domain.QuestionEntity;
import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.dto.question.QuestionSavedDTO;
import example.quiz.projection.question.QuestionProjection;

import java.util.List;

public interface QuestionService {

    List<QuestionProjection> getQuestionsByQuizId(Long quizId);

    void createQuestionSavedDTO(QuestionSavedDTO question);

    void createQuestionCreatedDTO(QuestionCreationDTO question);

    void deleteQuestion(Long id);

    QuestionProjection getQuestionById(Long id);

    QuestionEntity getQuestionEntityById(Long id);
}
