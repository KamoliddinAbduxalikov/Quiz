package example.quiz.dto.question;

import example.quiz.domain.QuizEntity;
import example.quiz.dto.option.OptionCreationDTO;

import java.util.List;

public record QuestionSavedDTO(
        String questionText,
        List<OptionCreationDTO> options,
        QuizEntity quiz
){
}
