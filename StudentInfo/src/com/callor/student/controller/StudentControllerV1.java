package com.callor.student.controller;

import com.callor.student.service.StudentService;
import com.callor.student.service.impl.StudentServiceImplV2;

public class StudentControllerV1 {
	public static void main(String[] args) {
		StudentService stService =  new StudentServiceImplV2();
		stService.inputStudent();
		stService.printStudent();
	}

}
