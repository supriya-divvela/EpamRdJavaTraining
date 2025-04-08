package com.epam.service;

import java.util.List;

import com.epam.model.Student;

public interface StudentService {
	public Student addStudent(Student student);
	public void removeStudent(int studentId);
	public Student updateStudent(Student student);
	public List<Student> getAllStudents();
}
