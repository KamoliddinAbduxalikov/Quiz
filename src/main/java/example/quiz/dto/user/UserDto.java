package example.quiz.dto.user;

import example.quiz.projection.user.UserProjection;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements UserProjection {
    private Long id;
    private String email;
    private String username;
}
