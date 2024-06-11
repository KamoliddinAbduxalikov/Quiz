package example.quiz.mapper;

import example.quiz.domain.OptionEntity;
import example.quiz.dto.option.OptionSavedDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OptionMapper {

    OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

    OptionEntity mapOptionSavedDTOToOptionEntity(OptionSavedDTO optionSavedDTO);
}
