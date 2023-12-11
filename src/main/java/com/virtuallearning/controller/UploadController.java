package com.virtuallearning.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.virtuallearning.Entity.Students;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

// ...

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final String GITHUB_REPO_PATH = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8Eb-mK4AHngeSBKFCPOZadMhbPwkB54aZIg&usqp=CAU";
	private static final String GITHUB_UPLOAD_DIR = "uploads";

	@GetMapping
	public String showUploadForm() {
		return "upload";
	}

	@PostMapping
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/upload";
		}

		// Get the phone number from the current session
		String phoneNumber = getPhoneNumberFromSession();

		try {
			byte[] bytes = file.getBytes();
			// Use the phone number as the image name
			String imageName = phoneNumber + ".jpg";
			Path path = Paths.get(GITHUB_REPO_PATH, GITHUB_UPLOAD_DIR, imageName);
			Files.write(path, bytes);
			redirectAttributes.addFlashAttribute("message", "File uploaded successfully!");
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Failed to upload the file.");
		}

		return "redirect:/upload";
	}

	private String getPhoneNumberFromSession() {
		// Get the current HTTP session
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
				.getSession();

		// Replace "phoneNumber" with the actual attribute or method to retrieve the
		// phone number from the session
		return (String) session.getAttribute("phNo");
	}
}
