package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.model.Student;
import com.epam.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepository;
	@Override
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void removeStudent(int studentId) {
		studentRepository.deleteById(studentId);
		
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

}
