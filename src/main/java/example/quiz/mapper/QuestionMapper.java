package example.quiz.mapper;

import example.quiz.domain.QuestionEntity;
import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.dto.question.QuestionSavedDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionEntity mapQuestionSavedDTOToQuestionEntity(QuestionSavedDTO questionSavedDTO);

    QuestionEntity mapQuestionCreationDTOToQuestionEntity(QuestionCreationDTO questionCreationDTO);

    List<QuestionEntity> mapQuestionCreationDTOListToQuestionEntityList(List<QuestionCreationDTO> questionCreationDTOList);
}
