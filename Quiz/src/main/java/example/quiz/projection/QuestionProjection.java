package example.quiz.projection;

import java.util.List;

public interface QuestionProjection {

    Long getId();

    String getContent();

    List<String> getOptions();

    String getCorrectAnswer();
}
