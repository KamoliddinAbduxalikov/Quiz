package example.quiz.projection.quiz;

import example.quiz.projection.question.QuestionProjection;

import java.util.List;

public interface QuizProjection {

    Long getId();

    String getTitle();

    List<QuestionProjection> getQuestions();
}
