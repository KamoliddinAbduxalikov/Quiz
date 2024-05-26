package example.quiz.service.impl;

import example.quiz.domain.AnswerEntity;
import example.quiz.domain.OptionEntity;
import example.quiz.domain.QuestionEntity;
import example.quiz.domain.UserEntity;
import example.quiz.dto.answer.AnswerSubmitDTO;
import example.quiz.repository.AnswerRepository;
import example.quiz.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserServiceImpl userServiceImpl;
    private final QuestionServiceImpl questionServiceImpl;
    private final OptionServiceImpl optionServiceImpl;

    public AnswerServiceImpl(AnswerRepository answerRepository, UserServiceImpl userServiceImpl, QuestionServiceImpl questionServiceImpl, OptionServiceImpl optionServiceImpl) {
        this.answerRepository = answerRepository;
        this.userServiceImpl = userServiceImpl;
        this.questionServiceImpl = questionServiceImpl;
        this.optionServiceImpl = optionServiceImpl;
    }

    @Override
    public Boolean submitAnswer(AnswerSubmitDTO answer) {
        UserEntity userEntity = userServiceImpl.getUserEntityById(answer.userId());
        QuestionEntity questionEntity = questionServiceImpl.getQuestionEntityById(answer.questionId());

        List<OptionEntity> options = optionServiceImpl.getOptionsByQuestionId(questionEntity.getId());

        OptionEntity option = options.stream()
                .filter(o -> o.getIsCorrect().equals(true)
                        && answer.givenAnswer().equals(o.getOptionText()))
                .findFirst().orElse(null);

        if (option != null) {
            answerRepository.save(
                    AnswerEntity.builder()
                            .question(questionEntity)
                            .user(userEntity)
                            .givenAnswer(answer.givenAnswer())
                            .build()
            );
            return true;
        }
        return false;
    }
}
