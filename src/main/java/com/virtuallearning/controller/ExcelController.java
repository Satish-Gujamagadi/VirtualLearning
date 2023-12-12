package com.virtuallearning.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.virtuallearning.Service.ExcelToCsvConverter;

@Controller
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelToCsvConverter excelToCsvConverter;

	@GetMapping("/uploadExcl")
	public String showUploadForm() {
		return "uploadForm";
	}

	@PostMapping("/uploadCSV")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
		try {
			String excelFilePath = "C:\\Users\\Satish G\\OneDrive\\Documents\\Uploads\\input.xlsx";
			String csvFilePath = "C:\\Users\\Satish G\\OneDrive\\Documents\\Uploads\\output.csv";

			// Save the uploaded Excel file
			file.transferTo(new File(excelFilePath));

			// Convert Excel to CSV
			
			excelToCsvConverter.convertExcelToCsv(excelFilePath, csvFilePath);

			model.addAttribute("successMessage", "File uploaded and converted successfully!");
			return "uploadForm";
		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error uploading or converting the file. Please try again.");
			return "uploadForm";
		}
	}
}
