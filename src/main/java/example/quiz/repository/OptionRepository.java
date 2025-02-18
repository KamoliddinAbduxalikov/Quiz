package example.quiz.repository;

import example.quiz.domain.OptionEntity;
import example.quiz.projection.option.OptionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {

    List<OptionEntity> findOptionEntitiesByQuestionId(Long questionId);

    List<OptionProjection> findOptionByQuestionId(Long questionId);
}
