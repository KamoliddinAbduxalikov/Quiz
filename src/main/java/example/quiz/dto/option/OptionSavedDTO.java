package example.quiz.dto.option;

import example.quiz.domain.QuestionEntity;

public record OptionSavedDTO(
        String optionText,
        Boolean isCorrect,
        QuestionEntity question
) {
}
