package example.quiz.dto.option;

import example.quiz.domain.QuestionEntity;

public record OptionSavedDTO(
        String text,
        Boolean isCorrect,
        QuestionEntity question
) {
}
