package example.quiz.service.impl;

import example.quiz.domain.QuestionEntity;
import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.mapper.QuestionMapper;
import example.quiz.projection.question.QuestionProjection;
import example.quiz.repository.QuestionRepository;
import example.quiz.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionProjection> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findQuestionsByQuizId(quizId);
    }

    @Override
    public void createQuestionSavedDTO(QuestionEntity question) {
        questionRepository.save(question);
    }

    @Override
    public void createQuestionCreatedDTO(QuestionCreationDTO question) {
        QuestionEntity questionEntity = QuestionMapper
                .INSTANCE
                .mapQuestionCreationDTOToQuestionEntity(question);
        questionRepository.save(questionEntity);
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
    public Optional<QuestionEntity> getQuestionEntityById(Long id) {
        return questionRepository.findById(id);
    }
}