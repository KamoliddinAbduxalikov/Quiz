package example.quiz.service.impl;

import example.quiz.domain.OptionEntity;
import example.quiz.dto.option.OptionSavedDTO;
import example.quiz.repository.OptionRepository;
import example.quiz.service.OptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;

    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public void save(OptionSavedDTO option) {
        optionRepository.save(
                OptionEntity.builder()
                        .optionText(option.text())
                        .isCorrect(option.isCorrect())
                        .question(option.question())
                        .build()
        );
    }

    @Override
    public List<OptionEntity> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findOptionEntitiesByQuestionId(questionId);
    }
}
