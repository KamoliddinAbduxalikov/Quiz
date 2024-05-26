package example.quiz.dto.web;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresMillsIn;

    public JwtResponse(String accessToken, Long expiresMillsIn) {
        this.accessToken = accessToken;
        this.refreshToken = null;
        this.tokenType = "Bearer";
        this.expiresMillsIn = expiresMillsIn;
    }
}
