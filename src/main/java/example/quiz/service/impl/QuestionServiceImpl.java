package example.quiz.service.impl;

import example.quiz.domain.QuestionEntity;
import example.quiz.dto.option.OptionSavedDTO;
import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.dto.question.QuestionSavedDTO;
import example.quiz.exceptions.ResourceNotFoundException;
import example.quiz.projection.question.QuestionProjection;
import example.quiz.repository.QuestionRepository;
import example.quiz.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final OptionServiceImpl optionServiceImpl;

    public QuestionServiceImpl(QuestionRepository questionRepository, OptionServiceImpl optionServiceImpl) {
        this.questionRepository = questionRepository;
        this.optionServiceImpl = optionServiceImpl;
    }

    @Override
    public List<QuestionProjection> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findQuestionsByQuizId(quizId);
    }

    @Override
    public void createQuestionSavedDTO(QuestionSavedDTO question) {
        QuestionEntity savedQuestion = questionRepository.save(
                QuestionEntity.builder()
                        .questionText(question.questionText())
                        .quiz(question.quiz())
                        .build()
        );

        for (int i = 0; i < question.options().size(); i++) {
            optionServiceImpl.save(
                    new OptionSavedDTO(
                            question.options().get(i).text(),
                            question.options().get(i).isCorrect(),
                            savedQuestion
                    )
            );
        }
    }

    @Override
    public void createQuestionCreatedDTO(QuestionCreationDTO question) {
        questionRepository.save(
                QuestionEntity.builder()
                        .questionText(question.questionText())
                        .build()
        );
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionProjection getQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }

    @Override
    public QuestionEntity getQuestionEntityById(Long id) {
        return questionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Question not found with id " + id)
        );
    }
}
