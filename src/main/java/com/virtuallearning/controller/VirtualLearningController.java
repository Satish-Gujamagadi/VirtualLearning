package com.virtuallearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VirtualLearningController {

	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/courses")
	public String courses(Model model) {
		return "Courses";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		return "Contact";
	}

	@GetMapping("/students")
	public String students(Model model) {
		// Add model attributes if needed
		return "Students"; 
		// This corresponds to the Thymeleaf template file (students.html)
	}

}
