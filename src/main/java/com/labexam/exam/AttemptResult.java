package com.labexam.exam;

import java.util.List;

public record AttemptResult(Exam exam, int score, int total, List<AnswerReview> answers) {

    public int percentage() {
        return total == 0 ? 0 : (int) Math.round(score * 100.0 / total);
    }
}