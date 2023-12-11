package com.virtuallearning.Service;

//StudentService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtuallearning.Entity.Students;
import com.virtuallearning.Repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public Students findStudentByEmailAndPassword(String email, String password) {
		return studentRepository.findStudentByEmailAndPassword(email, password);
	}

	public int checkStudent(Students student) {
		String email = student.getEmail();
		long phNo = student.getPhNo();

		// Check if the email already exists
		int ecount = studentRepository.countByEmail(email);
		int pcount = studentRepository.countByPhNo(phNo);

		if (ecount == 0 && pcount == 0) {
			return 0;
		}

		return 1;
	}

	public Students saveStudent(Students student) {
		// Save student to the database
		return studentRepository.save(student);
	}

}
