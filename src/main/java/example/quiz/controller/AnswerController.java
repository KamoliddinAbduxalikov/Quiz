package example.quiz.controller;

import example.quiz.domain.AnswerEntity;
import example.quiz.dto.answer.AnswerSubmitDTO;
import example.quiz.projection.quiz.QuizProjection;
import example.quiz.service.AnswerService;
import example.quiz.service.impl.AnswerServiceImpl;
import example.quiz.service.impl.QuizServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerServiceImpl;
    private final QuizServiceImpl quizServiceImpl;

    public AnswerController(AnswerServiceImpl answerServiceImpl, QuizServiceImpl quizServiceImpl) {
        this.answerServiceImpl = answerServiceImpl;
        this.quizServiceImpl = quizServiceImpl;
    }

    @GetMapping("/submit")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<QuizProjection>> getAllQuizzes(){
        List<QuizProjection> quizzes = quizServiceImpl.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/submit")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Boolean> submitAnswer(@RequestBody AnswerSubmitDTO answer) {
        Boolean isCorrect = answerServiceImpl.submitAnswer(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(isCorrect);
    }
}
