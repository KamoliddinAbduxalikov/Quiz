package example.quiz.service;

import example.quiz.domain.Quiz;
import example.quiz.projection.QuizProjection;
import example.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<QuizProjection> getAllQuizzes() {
        return quizRepository.findAllQuizzes();
    }

    public QuizProjection getQuizById(Long id) {
        return quizRepository.findQuizById(id);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
