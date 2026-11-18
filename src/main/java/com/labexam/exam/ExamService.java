package com.labexam.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExamService {

    private final JdbcTemplate jdbcTemplate;

    public ExamService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Exam> findAll() {
        return jdbcTemplate.query(
                "SELECT id, title, description FROM exams ORDER BY id",
                (rs, rowNum) -> new Exam(rs.getLong("id"), rs.getString("title"),
                        rs.getString("description"), List.of()));
    }

    public Exam findById(long id) {
        List<Exam> exams = jdbcTemplate.query(
                "SELECT id, title, description FROM exams WHERE id = ?",
                (rs, rowNum) -> new Exam(rs.getLong("id"), rs.getString("title"),
                        rs.getString("description"), findQuestions(rs.getLong("id"))),
                id);
        if (exams.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exam not found");
        }
        return exams.get(0);
    }

    public AttemptResult submit(long examId, Map<String, String> submittedAnswers) {
        Exam exam = findById(examId);
        List<AnswerReview> reviews = new ArrayList<>();
        int score = 0;

        for (Question question : exam.questions()) {
            String selectedOption = submittedAnswers.get("q" + question.id());
            boolean correct = question.correctOption().equals(selectedOption);
            if (correct) {
                score++;
            }
            reviews.add(new AnswerReview(
                    question.text(),
                    optionText(question, selectedOption),
                    optionText(question, question.correctOption()),
                    correct));
        }

        return new AttemptResult(exam, score, exam.questions().size(), reviews);
    }

    private List<Question> findQuestions(long examId) {
        return jdbcTemplate.query(
                """
                SELECT id, question_text, option_a, option_b, option_c, option_d, correct_option
                FROM questions WHERE exam_id = ? ORDER BY id
                """,
                (rs, rowNum) -> new Question(
                        rs.getLong("id"),
                        rs.getString("question_text"),
                        List.of(rs.getString("option_a"), rs.getString("option_b"),
                                rs.getString("option_c"), rs.getString("option_d")),
                        rs.getString("correct_option")),
                examId);
    }

    private String optionText(Question question, String option) {
        if (option == null) {
            return "Not answered";
        }
        try {
            int index = Integer.parseInt(option);
            return index >= 0 && index < question.options().size()
                    ? question.options().get(index)
                    : "Not answered";
        } catch (NumberFormatException exception) {
            return "Not answered";
        }
    }
}