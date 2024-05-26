package example.quiz.service.impl;

import example.quiz.domain.QuizEntity;
import example.quiz.dto.question.QuestionSavedDTO;
import example.quiz.dto.quiz.QuizCreationDTO;
import example.quiz.projection.quiz.QuizProjection;
import example.quiz.repository.QuizRepository;
import example.quiz.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionServiceImpl questionServiceImpl;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionServiceImpl questionServiceImpl) {
        this.quizRepository = quizRepository;
        this.questionServiceImpl = questionServiceImpl;
    }

    @Override
    public List<QuizProjection> getAllQuizzes() {
        return quizRepository.findAllQuizzes();
    }

    @Override
    public QuizProjection getQuizById(Long id) {
        return quizRepository.findQuizById(id);
    }

    @Override
    public void createQuiz(QuizCreationDTO quiz) {
        QuizEntity savedQuiz = quizRepository.save(
                QuizEntity.builder()
                        .title(quiz.title())
                        .build()
        );

        for (int i = 0; i < quiz.questions().size(); i++) {
            questionServiceImpl.createQuestionSavedDTO(new QuestionSavedDTO(
                    quiz.questions().get(i).questionText(),
                    quiz.questions().get(i).options(),
                    savedQuiz
            ));
        }
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}