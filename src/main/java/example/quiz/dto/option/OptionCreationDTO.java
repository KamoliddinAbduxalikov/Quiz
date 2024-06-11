package example.quiz.dto.option;

public record OptionCreationDTO(
        String optionText,
        Boolean isCorrect
) {
}
