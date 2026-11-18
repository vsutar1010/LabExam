package com.labexam.exam;

import java.util.List;

public record Exam(long id, String title, String description, List<Question> questions) {
}