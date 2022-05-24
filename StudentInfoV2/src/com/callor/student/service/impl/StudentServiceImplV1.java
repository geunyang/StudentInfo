package com.callor.student.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.callor.utils.Line;

/*
 * Service 클래스에서는 데이터를 관리(입력, 출력, 파일저장)을 할텐데
 * 내부 전용으로 선언하는 방법이 있고
 * 외부에서(Controller) 에서 선언하여 매개변수로 주입하는 방법이 있다
 * V1 에서는 내부 전용으로 선언하는 방법을 사용하자
 */
public class StudentServiceImplV1 implements StudentService {

	// 클래스영역(필드영역)에서 객체를 선언하고
	// 기본 생성자에서 객체를 초기화(생성)하여 사용하기
	// 상속 해주기 위해서 protected로 선언
	protected final List<StudentVO> stdList;
	protected final String fileName;

	public StudentServiceImplV1() {
		this(new ArrayList<>(), "src/com/callor/student/student.txt");
	}

	public StudentServiceImplV1(List<StudentVO> stdList, String fileName) {
		this.stdList = stdList;
		this.fileName = fileName;
		loadStudent();
	}

	/*
	 * Controller 에서 ServiceV1 클래스를 초기화 할때 student.txt 파일에서 학생 정보를 읽어 stdList 에 담아두는
	 * 코드를 작성하기
	 */
	protected void loadStudent() {

		// is 객체와 file 객체를 선언만
		InputStream is = null;
		Scanner file = null;

		try {
			// 파일 이름을 전달하여 is 객체를 생성
			// student.txt 파일을 열어서 읽을 준비하라
			is = new FileInputStream(fileName);
			// FileInputStream 을 Scanner 와 연결하는 순간
			// 이미 운영체제는 student.txt 파일을 모두 읽어서 사용할 준비를 마쳐준다
			file = new Scanner(is);

			// 읽을 내용이 있나?
			// 있으면 file.hasNext()가 true 가 되고
			// while(){} 내부의 코드가 실행된다
			while (file.hasNext()) {
				String line = file.nextLine();
				String[] strInfo = line.split(":");

				StudentVO stVO = StudentVO.builder().num(strInfo[0]).name(strInfo[1]).dept(strInfo[2])
						.grade(Integer.valueOf(strInfo[3])).tel(strInfo[4]).build();

				stdList.add(stVO);

			} // end while
			file.close();
			is.close();
			// 단계별로 Exception 처리
			/*
			 * Exception 의 계층구조 Exception 은 각 등급별로 계층 구조를 갖는다 가장 상급의 Exception 은 순수한
			 * Exception 키워드로 처리를 한다 보통 catch 의 exception 처리는 낮은 등급부터 순서대로 처리하며 가장 등급이 높은 순수
			 * Exception 은 가장 마지막에 catch를 실행한다
			 */
		} catch (FileNotFoundException e) {
			System.out.println(fileName + "파일이없음");
			// return 을 사용하는 이유
			// try-catch {} 명령문 이후에 실행해야할 코드가 있을 수 있으므로
			// exception 이 발생하면 더이상 진행하지 않도록 하기 위함이다
			return;
		} catch (IOException e) {
			System.out.println(" IO 오류");
			return;
		} catch (Exception e) {
			System.out.println("원인을 알 수 없는 Exception");
			return;
		}

	}

	// 여기서부터 놓침 다시 작성
	@Override
	public void inputStudent() {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(Line.dLine(50));
			System.out.println("학생정보입력");
			System.out.println(Line.sLine(50));

			System.out.println("학번(QUIT:종료) >> ");
			String num = scan.nextLine();
			if (num.equals("QUIT")) {
				break;
			}
			System.out.println("이름(QUIT:종료) >> ");
			String name = scan.nextLine();
			if (num.equals("QUIT")) {
				break;
			}
			System.out.println("학과(QUIT:종료) >> ");
			String dept = scan.nextLine();
			if (dept.equals("QUIT")) {
				break;
			}
			System.out.println("학년(QUIT:종료) >> ");
			String strGrade = scan.nextLine();
			if (strGrade.equals("QUIT")) {
				break;
			}
			int intGrade = 0;
			try {
				intGrade = Integer.valueOf(strGrade);
			} catch (Exception e) {
				System.out.println("학년은 숫자로 입력");
				continue;
			}
			System.out.println("전화번호(QUIT:종료) >> ");
			String tel = scan.nextLine();
			if (tel.equals("QUIT")) {
				break;
			}
			StudentVO stVO = StudentVO.builder().num(num).name(name).grade(intGrade).dept(dept).tel(tel).build();
			stdList.add(stVO);
		}// end while
		saveStudent();
		printStudent();

	}


	protected void saveStudent() {
		OutputStream os = null;
		PrintWriter out = null;

		try {
			os = new FileOutputStream(fileName);
			out =  new PrintWriter(os);
			for(StudentVO stVO : stdList) {
				out.printf("%s:",stVO.getNum());
				out.printf("%s:",stVO.getName());
				out.printf("%s:",stVO.getDept());
				out.printf("%s:",stVO.getGrade());
				out.printf("%s:",stVO.getTel());
			}
			out.flush();
			out.close();
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
		}
	}

	@Override
	public void printStudent() {

		System.out.println(Line.dLine(50));
		System.out.println("대한고교 학생정보");
		System.out.println(Line.dLine(50));

		System.out.println("학번\t이름\t학과\t학년\t전화번호");
		System.out.println(Line.sLine(50));

		for (StudentVO stVO : stdList) {
			System.out.print(stVO.getNum() + "\t");
			System.out.print(stVO.getName() + "\t");
			System.out.print(stVO.getDept() + "\t");
			System.out.print(stVO.getGrade() + "\t");
			System.out.println(stVO.getTel() + "\t");
		}
		System.out.println(Line.dLine(50));
	}

}
