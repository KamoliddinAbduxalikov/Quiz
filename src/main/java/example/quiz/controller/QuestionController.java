package example.quiz.controller;

import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.projection.question.QuestionProjection;
import example.quiz.service.impl.QuestionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionServiceImpl questionServiceImpl;

    public QuestionController(QuestionServiceImpl questionServiceImpl) {
        this.questionServiceImpl = questionServiceImpl;
    }

    @GetMapping("/quiz/{id}")
    public List<QuestionProjection> getQuestionsByQuizId(@PathVariable Long id) {
        return questionServiceImpl.getQuestionsByQuizId(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> createQuestion(@RequestBody QuestionCreationDTO questionEntity) {
        questionServiceImpl.createQuestionCreatedDTO(questionEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteQuestion(@PathVariable Long id) {
        questionServiceImpl.deleteQuestion(id);
    }
}
