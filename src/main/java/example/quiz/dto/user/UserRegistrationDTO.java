package example.quiz.dto.user;

public record UserRegistrationDTO(
        String username,
        String email,
        String password
) {
}
