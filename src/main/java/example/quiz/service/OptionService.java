package example.quiz.service;

import example.quiz.domain.OptionEntity;
import example.quiz.projection.option.OptionProjection;

import java.util.List;

public interface OptionService {

    void saveOption(OptionEntity options);

    List<OptionEntity> getOptionsByQuestionId(Long questionId);

    List<OptionProjection> getOptionByQuestionId(Long questionId);
}
