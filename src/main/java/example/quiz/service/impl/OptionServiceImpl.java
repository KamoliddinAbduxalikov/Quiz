package example.quiz.service.impl;

import example.quiz.domain.OptionEntity;
import example.quiz.projection.option.OptionProjection;
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
    public void saveOption(OptionEntity options) {
        optionRepository.save(options);
    }

    @Override
    public List<OptionEntity> getOptionsByQuestionId(Long questionId) {
        return optionRepository.findOptionEntitiesByQuestionId(questionId);
    }

    @Override
    public List<OptionProjection> getOptionByQuestionId(Long questionId) {
        return optionRepository.findOptionByQuestionId(questionId);
    }
}
