package example.quiz.service;

import example.quiz.domain.OptionEntity;
import example.quiz.dto.option.OptionSavedDTO;

import java.util.List;

public interface OptionService {

    void save(OptionSavedDTO option);

    List<OptionEntity> getOptionsByQuestionId(Long questionId);
}
