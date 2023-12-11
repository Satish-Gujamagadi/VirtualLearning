package com.virtuallearning.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtuallearning.Entity.Students;
import com.virtuallearning.Service.StudentService;

@Controller
public class LoginController {

	String profilepic = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8Eb-mK4AHngeSBKFCPOZadMhbPwkB54aZIg&usqp=CAU";
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/home")
	public String showLoginForm(Model model, HttpSession session) {

		// Retrieve userName from the session
		String userName = (String) session.getAttribute("userName");
		if (userName != null) {
			model.addAttribute("userName", userName);
		}

		return "login"; 
	}

	@PostMapping("/login")
	public String processLogin(@RequestParam String email, @RequestParam String password, Model model,
			HttpSession session) {

		// Validate credentials
		Students student = studentService.findStudentByEmailAndPassword(email, password);
		if (student != null) {
			// Successful login
			model.addAttribute("userName", student.getName());
			model.addAttribute("prpic", profilepic);
			session.setAttribute("userName", student.getName());

			return "login"; // Redirect to home page or any other page
		} else {
			// Failed login
			model.addAttribute("error", "Invalid email or password");
			return "index"; // Redirect back to the login page with an error message
		}
	}
	
	@GetMapping("/MyCourses")
	public String mycourses(Model model, HttpSession session) {

		// Retrieve userName from the session
		String userName = (String) session.getAttribute("userName");
		if (userName != null) {
			model.addAttribute("userName", userName);
		}

		return "MyCourses"; 
	}

	@PostMapping("/logout")
	public String logOutPage(Model model, HttpSession session) {
		session.invalidate();
		model.asMap().clear();
		model.addAttribute("logout", "Logged out successfully");
		return "index";
	}

}
