package example.quiz.service;

import example.quiz.domain.UserEntity;
import example.quiz.dto.user.UserLoginDTO;
import example.quiz.dto.user.UserRegistrationDTO;
import example.quiz.dto.web.JwtResponse;
import example.quiz.projection.user.UserProjection;

import java.util.List;

public interface UserService {

    void register(UserRegistrationDTO data);

    JwtResponse login(UserLoginDTO data);

    List<UserProjection> getUsers();

    UserEntity getUserEntityById(Long id);

    UserProjection getUserProjectionById(Long id);
}
