package com.labexam.exam;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/")
    public String listExams(Model model) {
        model.addAttribute("exams", examService.findAll());
        return "exams";
    }

    @GetMapping("/exams/{id}")
    public String showExam(@PathVariable long id, Model model) {
        model.addAttribute("exam", examService.findById(id));
        return "exam";
    }

    @PostMapping("/exams/{id}/submit")
    public String submitExam(
            @PathVariable long id,
            @RequestParam Map<String, String> answers,
            Model model) {
        model.addAttribute("result", examService.submit(id, answers));
        return "result";
    }
}