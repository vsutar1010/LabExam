package com.labexam.exam;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.labexam.LabExamApplication;

@SpringBootTest(classes = LabExamApplication.class)
class ExamServiceTest {

    @Autowired
    private ExamService examService;

    @Test
    void loadsSampleExamsAndGradesSubmittedAnswers() {
        assertEquals(2, examService.findAll().size());

        Exam exam = examService.findById(1);
        assertEquals(5, exam.questions().size());

        AttemptResult result = examService.submit(1, Map.of(
                "q1", "1",
                "q2", "2",
                "q3", "0",
                "q4", "1"));

        assertEquals(3, result.score());
        assertEquals(5, result.total());
        assertEquals(60, result.percentage());
        assertTrue(result.answers().get(0).correct());
        assertFalse(result.answers().get(2).correct());
        assertEquals("Not answered", result.answers().get(4).selectedAnswer());
    }

    @Test
    void percentageIsZeroWhenExamHasNoQuestions() {
        AttemptResult result = new AttemptResult(
                new Exam(1, "Empty exam", "No questions", List.of()),
                0,
                0,
                List.of());

        assertEquals(0, result.percentage());
    }
}