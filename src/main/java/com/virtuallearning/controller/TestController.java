package com.virtuallearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtuallearning.Entity.McqQuestion;
import com.virtuallearning.Service.McqQuestionService;

@Controller
public class TestController {

	@Autowired
	private McqQuestionService mcqQuestionService;

	
		
		

	@GetMapping("/test")
	public String getTestPage(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "1") int size) {

		model.addAttribute("timeLeft", 60 * 60); // Initial time in seconds (60 minutes)
		 Page<McqQuestion> questions = mcqQuestionService.getAllQuestions(PageRequest.of(page, size));

		model.addAttribute("questions", questions);

		// Add other necessary attributes...

		return "test";
	}
}