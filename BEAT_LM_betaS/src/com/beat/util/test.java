package com.beat.util;

public class test {

	String sql1="insert into Lecture_Room("
			+ "lectSeriNum, lectureName, teacherName, studentTotal, "
			+ "lectureStart, lectureEnd) "
			+ "values(lecture_room_seq.nextval, ?, (select lecturerName from lecturer where lecturerCode=?) , ?, ?, ?)";
	// ���� �ø����� �������� �ڵ��Է�, ���Ǹ�, �����(�̰� ����� ���̺��� lecturer���� 1,2,3 �߿� �ϳ��� ���� �� �� �ֵ��� ��, �����ѿ�(�⺻���� 0), ���� ������, ���������� ����.
	// ���� 1,2,3�� ���� �ø��� 
	
	
	String sql2="select lectureName, teacherName, lectureStart, lectureEnd, studentTotal,"
			+ " ";

}
