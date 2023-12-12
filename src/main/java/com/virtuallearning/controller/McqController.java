package com.virtuallearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.virtuallearning.Service.McqQuestionService;

@Controller
@RequestMapping("/mcq")
public class McqController {

	@Autowired
	private McqQuestionService mcqQuestionService;

	@GetMapping("/getcsv")
	public String getCSV() {
		return "saveMCQ";
	}

	@PostMapping("/postcsv")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		Path filePath = Paths.get("C:", "Users", "Satish G", "Desktop", "Uploads", "yourfile.csv");

		mcqQuestionService.uploadCsvToDatabase(file, filePath);
		return ResponseEntity.ok("File uploaded successfully!");
	}
}
