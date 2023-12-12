package com.virtuallearning.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.virtuallearning.Entity.McqQuestion;
import com.virtuallearning.Repository.McqQuestionRepository;

@Service
public class McqQuestionService {
	@Autowired
	private McqQuestionRepository mcqQuestionRepository;

	public void uploadCsvToDatabase(MultipartFile file, Path csvFilePath) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				McqQuestion mcqQuestion = new McqQuestion();
				mcqQuestion.setQuestion(data[0]);
				mcqQuestion.setOption1(data[1]);
				mcqQuestion.setOption2(data[2]);
				mcqQuestion.setOption3(data[3]);
				mcqQuestion.setOption4(data[4]);
				mcqQuestionRepository.save(mcqQuestion);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Page<McqQuestion> getAllQuestions(Pageable pageable) {
		return mcqQuestionRepository.findAll(pageable);
	}
}
