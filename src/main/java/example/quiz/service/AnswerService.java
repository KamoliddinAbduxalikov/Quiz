package example.quiz.service;

import example.quiz.dto.answer.AnswerSubmitDTO;

public interface AnswerService {

    Boolean submitAnswer(AnswerSubmitDTO answer);
}
