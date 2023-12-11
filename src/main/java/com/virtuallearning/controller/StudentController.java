package com.virtuallearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.virtuallearning.Entity.Students;
import com.virtuallearning.Service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/signup")
	public String signUp(Students student, Model model) {

		int students = studentService.checkStudent(student);
		if (students == 0) {
			// Save student to the database
			Students savedStudent = studentService.saveStudent(student);
			// Set success message
			model.addAttribute("message", "Data saved Successfully " + savedStudent.getName());
		} else {
			// Set error message
			model.addAttribute("message", "Data with this email/Phone number already exist.!!!");
		}
		return "signup";
	}

	@GetMapping("/signup")
	public String showSignupForm(Model model) {
		// Create a new Students object to be used in the form
		Students student = new Students();
		model.addAttribute("students", student); // Use "students" instead of "student"
		return "signup";
	}

}
