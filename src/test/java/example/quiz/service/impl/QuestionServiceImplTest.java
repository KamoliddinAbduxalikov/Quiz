package example.quiz.service.impl;

import example.quiz.domain.QuestionEntity;
import example.quiz.dto.option.OptionCreationDTO;
import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.exceptions.ResourceNotFoundException;
import example.quiz.projection.option.OptionProjection;
import example.quiz.projection.question.QuestionProjection;
import example.quiz.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private QuestionEntity questionEntity;
    private QuestionProjection questionProjection;
    private QuestionCreationDTO questionCreationDTO;

    @BeforeEach
    void setUp() {
        questionEntity = new QuestionEntity();
        questionEntity.setId(1L);
        questionEntity.setQuestionText("What is the capital of France?");

        questionProjection = new QuestionProjection() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getQuestionText() {
                return "What is the capital of France?";
            }

            @Override
            public List<OptionProjection> getOptions() {
                return getTestOptionProjections().toList();
            }
        };

        questionCreationDTO = new QuestionCreationDTO(
                "What is the capital of France?",
                getTestOptionCreationDTOs().toList()
                );
    }

    @Test
    void testGetQuestionsByQuizId() {
        when(questionRepository.findQuestionsByQuizId(1L)).thenReturn(Arrays.asList(questionProjection));

        List<QuestionProjection> questions = questionService.getQuestionsByQuizId(1L);
        assertNotNull(questions);
        assertEquals(1, questions.size());
        assertEquals(questionProjection.getQuestionText(), questions.get(0).getQuestionText());
    }

    @Test
    void testCreateQuestionSavedDTO() {
        questionService.createQuestionSavedDTO(questionEntity);
        verify(questionRepository, times(1)).save(questionEntity);
    }

    @Test
    void testCreateQuestionCreatedDTO() {
        when(questionRepository.save(any(QuestionEntity.class))).thenReturn(questionEntity);

        questionService.createQuestionCreatedDTO(questionCreationDTO);

        verify(questionRepository, times(1)).save(any(QuestionEntity.class));
    }

    @Test
    void testDeleteQuestion() {
        questionService.deleteQuestion(1L);
        verify(questionRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetQuestionById() {
        when(questionRepository.findQuestionById(1L)).thenReturn(questionProjection);

        QuestionProjection question = questionService.getQuestionById(1L);
        assertNotNull(question);
        assertEquals(questionProjection.getQuestionText(), question.getQuestionText());
    }

    @Test
    void testGetQuestionEntityById() {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(questionEntity));

        Optional<QuestionEntity> question = questionService.getQuestionEntityById(1L);
        assertNotNull(question);
        assertEquals(questionEntity.getQuestionText(), question.get().getQuestionText());
    }

    @Test
    void testGetQuestionEntityByIdNotFound() {
        when(questionRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            questionService.getQuestionEntityById(1L);
        });

        String expectedMessage = "Question not found with id 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    private static Stream<OptionProjection> getTestOptionProjections() {
        return Stream.of(
                new OptionProjection() {
                    @Override
                    public Long getId() {
                        return 1L;
                    }

                    @Override
                    public String getOptionText() {
                        return "Hello";
                    }

                    @Override
                    public Boolean getIsCorrect() {
                        return true;
                    }
                },
                new OptionProjection() {
                    @Override
                    public Long getId() {
                        return 1L;
                    }

                    @Override
                    public String getOptionText() {
                        return "Hello";
                    }

                    @Override
                    public Boolean getIsCorrect() {
                        return true;
                    }
                }
        );
    }

    private static Stream<OptionCreationDTO> getTestOptionCreationDTOs() {
        return Stream.of(
                new OptionCreationDTO("Hello",true),
                new OptionCreationDTO("Welcome",false),
                new OptionCreationDTO("Thank you",false)
        );
    }
}