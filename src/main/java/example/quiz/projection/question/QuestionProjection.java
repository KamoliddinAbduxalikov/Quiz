package example.quiz.projection.question;

import example.quiz.projection.option.OptionProjection;

import java.util.List;

public interface QuestionProjection {

    Long getId();
    String getQuestionText();
    List<OptionProjection> getOptions();
}
