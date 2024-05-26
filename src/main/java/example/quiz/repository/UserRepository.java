package example.quiz.repository;

import example.quiz.domain.UserEntity;
import example.quiz.projection.user.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u")
    List<UserProjection> findAllUsers();

    @Query("SELECT u FROM UserEntity u WHERE u.id=:id")
    UserProjection findUserProjectionById(Long id);
}
