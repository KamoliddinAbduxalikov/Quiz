package example.quiz.mapper;

import example.quiz.domain.QuizEntity;
import example.quiz.dto.quiz.QuizCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuizMapper {

     QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

     QuizEntity mapQuizCreationDTOToQuizEntity(QuizCreationDTO dto);
}
