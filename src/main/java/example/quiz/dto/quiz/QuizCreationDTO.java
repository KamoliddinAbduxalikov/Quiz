package example.quiz.dto.quiz;

import example.quiz.dto.question.QuestionCreationDTO;

import java.util.List;

public record QuizCreationDTO(
        String title,
        List<QuestionCreationDTO> questions
) {
}
