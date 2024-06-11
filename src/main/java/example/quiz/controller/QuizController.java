package example.quiz.controller;

import example.quiz.dto.quiz.QuizCreationDTO;
import example.quiz.projection.quiz.QuizProjection;
import example.quiz.service.impl.QuizServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizServiceImpl quizServiceImpl;

    public QuizController(QuizServiceImpl quizServiceImpl) {
        this.quizServiceImpl = quizServiceImpl;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<QuizProjection>> getAllQuizzes() {
        List<QuizProjection> quizzes = quizServiceImpl.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<QuizProjection> getQuizById(@PathVariable Long id) {
        QuizProjection quiz = quizServiceImpl.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> createQuiz(@RequestBody QuizCreationDTO quiz) {
        quizServiceImpl.createQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizServiceImpl.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}