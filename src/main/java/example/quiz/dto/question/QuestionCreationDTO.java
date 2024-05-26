package example.quiz.dto.question;

import example.quiz.dto.option.OptionCreationDTO;

import java.util.List;

public record QuestionCreationDTO(
        String questionText,
        List<OptionCreationDTO> options
) {
}
