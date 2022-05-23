package com.callor.student.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;

public class StudentServiceImplV1 implements StudentService {

		protected final Scanner scan;
		protected final List<StudentVO> stList;
		
		public StudentServiceImplV1() {
			stList = new ArrayList<StudentVO>();
			scan =  new Scanner(System.in);
		}
		

		@Override
		public void inputStudent() {
			
			while(true) {
				// 학번 입력
				System.out.println("학번 입력(QUIT : 종료) >> ");
				String stNum = scan.nextLine();
				// QUIT 입력하면 break;
				if(stNum.equals("QUIT")) {
					break;
				}
				
				
				// 
				System.out.println("이름 입력 >>");
				String stName = scan.nextLine();
				// 
				System.out.println("학과 입력 >>");
				String stDept = scan.nextLine();
				// 
				System.out.println("학년 입력 >>");
				String stGrade = scan.nextLine();

				System.out.println("전화번호 입력 >>");
				String stTel = scan.nextLine();
				
				// VO 생성
				StudentVO stVO = new StudentVO();
				// VO 에 Setting
				stVO.setNum(stNum);
				stVO.setName(stName);
				stVO.setDept(stDept);
				stVO.setGrage(stGrade);
				stVO.setTel(stTel);
				
				// scList.add()
				stList.add(stVO);
			} // end while
			System.out.println("입력이 중단되었습니다.");
			
			
		
	}

	@Override
	public void printStudent() {
		
		
	}

}
