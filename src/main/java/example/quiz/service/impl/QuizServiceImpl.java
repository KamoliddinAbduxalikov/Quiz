package example.quiz.service.impl;

import example.quiz.domain.QuestionEntity;
import example.quiz.domain.QuizEntity;
import example.quiz.dto.quiz.QuizCreationDTO;
import example.quiz.mapper.QuizMapper;
import example.quiz.projection.quiz.QuizProjection;
import example.quiz.repository.QuizRepository;
import example.quiz.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionServiceImpl questionServiceImpl;
    private final OptionServiceImpl optionServiceImpl;

    public QuizServiceImpl(QuizRepository quizRepository,
                           QuestionServiceImpl questionServiceImpl,
                           OptionServiceImpl optionServiceImpl) {
        this.quizRepository = quizRepository;
        this.questionServiceImpl = questionServiceImpl;
        this.optionServiceImpl = optionServiceImpl;
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
        QuizEntity quizEntity = QuizMapper.INSTANCE.mapQuizCreationDTOToQuizEntity(quiz);
        for (int i = 0; i < quizEntity.getQuestions().size(); i++) {
            quizEntity.getQuestions().get(i).setQuiz(quizEntity);
            questionServiceImpl.createQuestionSavedDTO(quizEntity.getQuestions().get(i));
        }

        for (QuestionEntity questionEntity : quizEntity.getQuestions()) {
            for (int j = 0; j < questionEntity.getOptions().size(); j++) {
                questionEntity.getOptions().get(j).setQuestion(questionEntity);
                optionServiceImpl.saveOption(questionEntity.getOptions().get(j));
            }
        }
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}