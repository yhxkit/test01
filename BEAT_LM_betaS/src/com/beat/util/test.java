package com.beat.util;

public class test {

	String sql1="insert into Lecture_Room("
			+ "lectSeriNum, lectureName, teacherName, studentTotal, "
			+ "lectureStart, lectureEnd) "
			+ "values(lecture_room_seq.nextval, ?, (select lecturerName from lecturer where lecturerCode=?) , ?, ?, ?)";
	// 강의 시리얼은 시퀀스로 자동입력, 강의명, 강사명(이건 강사명 테이블인 lecturer에서 1,2,3 중에 하나를 고르면 할 수 있도록 함, 강사총원(기본값은 0), 강의 시작일, 강의종료일 순서.
	// 강의 1,2,3은 강의 시리얼 
	
	
	String sql2="select lectureName, teacherName, lectureStart, lectureEnd, studentTotal,"
			+ " ";

}
