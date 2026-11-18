package com.labexam.exam;

public record AnswerReview(
        String question,
        String selectedAnswer,
        String correctAnswer,
        boolean correct) {
}