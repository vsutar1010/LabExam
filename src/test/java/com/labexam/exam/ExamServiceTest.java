package com.labexam.exam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import com.labexam.LabExamApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        assertTrue(result.answers().get(0).correct());
        assertFalse(result.answers().get(2).correct());
        assertEquals("Not answered", result.answers().get(4).selectedAnswer());
    }
}