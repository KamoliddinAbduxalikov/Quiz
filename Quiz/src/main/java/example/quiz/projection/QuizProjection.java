package example.quiz.projection;

import java.util.List;

public interface QuizProjection {

    Long getId();

    String getTitle();

    List<QuestionProjection> getQuestions();
}
