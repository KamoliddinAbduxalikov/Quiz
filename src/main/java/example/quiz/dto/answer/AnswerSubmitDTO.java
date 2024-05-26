package example.quiz.dto.answer;

public record AnswerSubmitDTO(
        Long questionId,
        Long userId,
        String givenAnswer
) {
}
