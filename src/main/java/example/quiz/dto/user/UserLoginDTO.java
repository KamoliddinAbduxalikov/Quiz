package example.quiz.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "This is User Login ")
public record UserLoginDTO(
        @Schema(description = "This is user email")
        String email,
        String password
) {
}
