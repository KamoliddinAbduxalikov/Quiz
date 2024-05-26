package example.quiz.dto.question;

import example.quiz.projection.option.OptionProjection;
import example.quiz.projection.question.QuestionProjection;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto implements QuestionProjection {
    private Long id;
    private String questionText;
    private List<OptionProjection> options;
}