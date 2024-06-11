package example.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.quiz.dto.option.OptionCreationDTO;
import example.quiz.dto.question.QuestionCreationDTO;
import example.quiz.dto.quiz.QuizCreationDTO;
import example.quiz.projection.question.QuestionProjection;
import example.quiz.projection.quiz.QuizProjection;
import example.quiz.service.impl.QuizServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuizServiceImpl quizService;

    @InjectMocks
    private QuizController quizController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllQuizzes() throws Exception {
        List<QuizProjection> mockQuizzes = Arrays.asList(
                new QuizProjection() {
                    @Override
                    public Long getId() {
                        return 1L;
                    }

                    @Override
                    public String getTitle() {
                        return "France";
                    }

                    @Override
                    public List<QuestionProjection> getQuestions() {
                        return List.of();
                    }
                },
                new QuizProjection() {
                    @Override
                    public Long getId() {
                        return 2L;
                    }

                    @Override
                    public String getTitle() {
                        return "Uzbekistan";
                    }

                    @Override
                    public List<QuestionProjection> getQuestions() {
                        return List.of();
                    }
                }
        );

        when(quizService.getAllQuizzes()).thenReturn(mockQuizzes);

        mockMvc.perform(get("/quiz/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockQuizzes))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("France"));
    }

    @Test
    void getQuizById() throws Exception {
        QuizProjection quizProjection = new QuizProjection() {
            @Override
            public Long getId() {
                return 1L;
            }

            @Override
            public String getTitle() {
                return "Hello";
            }

            @Override
            public List<QuestionProjection> getQuestions() {
                return List.of();
            }
        };

        when(quizService.getQuizById(quizProjection.getId())).thenReturn(quizProjection);

        mockMvc.perform(get("/quiz/{id}",quizProjection.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Hello"));
    }

    @Test
    void createQuiz() throws Exception {
        final var dto = new QuizCreationDTO("France", testGetQuestionCreationDTOs().toList());

        doNothing().when(quizService).createQuiz(any(QuizCreationDTO.class));

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/quiz/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
        resultActions.andExpect(status().isCreated());
        verify(quizService).createQuiz(any());
    }

    @Test
    void deleteQuiz() throws Exception {
        doNothing().when(quizService).deleteQuiz(1L);

        mockMvc.perform(delete("/quiz/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    private static Stream<QuestionCreationDTO> testGetQuestionCreationDTOs() {
        return Stream.of(
                new QuestionCreationDTO("What's up", testGetOptionCreationDTOs().toList()),
                new QuestionCreationDTO("How are you", testGetOptionCreationDTOs().toList()),
                new QuestionCreationDTO("Who are you", testGetOptionCreationDTOs().toList()),
                new QuestionCreationDTO("Where are you from", testGetOptionCreationDTOs().toList())
        );
    }

    private static Stream<OptionCreationDTO> testGetOptionCreationDTOs() {
        return Stream.of(
                new OptionCreationDTO("Hello", true),
                new OptionCreationDTO("Welcome", false),
                new OptionCreationDTO("Please", false),
                new OptionCreationDTO("Thank you", false)
        );
    }
}