package example.quiz.service.impl;

import example.quiz.domain.UserEntity;
import example.quiz.dto.user.UserLoginDTO;
import example.quiz.dto.user.UserRegistrationDTO;
import example.quiz.dto.web.JwtResponse;
import example.quiz.exceptions.UserAlreadyExistsException;
import example.quiz.exceptions.UserNotFoundException;
import example.quiz.projection.UserProjection;
import example.quiz.provider.JwtProvider;
import example.quiz.repository.UserRepository;
import example.quiz.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void register(UserRegistrationDTO data) {
        if (userRepository.existsByEmail(data.email())) {
            throw new UserAlreadyExistsException("There is a user in this email " + data.email());
        }
        userRepository.save(
                UserEntity.builder()
                        .email(data.email())
                        .username(data.username())
                        .password(passwordEncoder.encode(data.password()))
                        .build()
        );
    }

    @Override
    public JwtResponse login(UserLoginDTO data) {
        final UserEntity user = userRepository.findByEmail(data.email())
                .orElseThrow(
                        () -> new UserNotFoundException("User not found on " + data.email() + " email")
                );
        return new JwtResponse(jwtProvider.generateToken(user), jwtProvider.getExpiration());
    }

    @Override
    public List<UserProjection> getUsers() {
        return userRepository.findAllUsers();
    }
}
