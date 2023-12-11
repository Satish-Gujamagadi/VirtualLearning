package com.virtuallearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimeController {

	@GetMapping("/test")
	public String index(Model model) {
		model.addAttribute("timeLeft", 60 * 60); // Initial time in seconds (60 minutes)
		return "Test";
	}
}