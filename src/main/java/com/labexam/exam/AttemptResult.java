package com.labexam.exam;

import java.util.List;

public record AttemptResult(Exam exam, int score, int total, List<AnswerReview> answers) {
}