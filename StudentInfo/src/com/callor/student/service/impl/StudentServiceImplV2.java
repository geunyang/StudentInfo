package com.callor.student.service.impl;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.callor.student.model.StudentVO;

public class StudentServiceImplV2 extends StudentServiceImplV1{
	
	protected String saveFileName;
	public StudentServiceImplV2() {
		super();
		saveFileName = "src/com/callor/student/data/student.txt";
	}
	
	@Override
	public void printStudent() {
		
		// 파일에 데이터를 기록하는데 사용하는 도구
		FileOutputStream fileOut = null;
		BufferedOutputStream buffer = null;

		try {
			fileOut =  new FileOutputStream(saveFileName);
			buffer =  new BufferedOutputStream(fileOut);
			for(StudentVO stVO : stList) {
				String writeStr = "";
				writeStr += String.format("%d : ", stVO.getNum());
				writeStr += String.format("%d : ", stVO.getName());
				writeStr += String.format("%d : ", stVO.getDept());
				writeStr += String.format("%d : ", stVO.getGrage());
				writeStr += String.format("%d\n ", stVO.getTel());
				
				buffer.write(writeStr.getBytes());
			} // end for
			// 데이터 입력 후 저장에 꼭 필요한 코드
			buffer.flush();
			buffer.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
