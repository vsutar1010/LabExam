package com.labexam.exam;

import java.util.List;

public record Question(long id, String text, List<String> options, String correctOption) {
}