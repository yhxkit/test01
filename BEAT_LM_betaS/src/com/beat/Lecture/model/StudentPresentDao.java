package com.beat.Lecture.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beat.Lecture.model.StudentGradeDto;
import com.beat.Lecture.model.StudentPresentDto;
import com.beat.util.LMSDao;

public class StudentPresentDao extends LMSDao {
	public StudentPresentDao() {
		conn = super.conn;
	}

	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<StudentPresentDto_john> studentPresentList() {
		String sql = "SELECT ";
		sql += "Lecture_Room.LECTSERINUM as LECTSERINUM, "; // 강의번호
		sql += "Lecture_Room.LECTURENAME as LECTURENAME, "; // 강의명
		sql += "Class_Information.MNUM as MNUM, "; // 회원번호
		sql += "lmsMember.MID as MID, "; // 회원 아이디
		sql += "lmsMember.MNAME as MNAME, "; // 회원 이름
		sql += "trunc(Class_Information.PRESENTDAYS/20*100) as presentRatio "; // 출석률
		sql += "from Lecture_Room, Class_Information, lmsMember ";
		sql += "where ";
		sql += "Class_Information.lectSeriNum = Lecture_Room.lectSeriNum ";
		sql += "and lmsMember.mnum = Class_Information.mnum";
		ArrayList<StudentPresentDto_john> list = new ArrayList<StudentPresentDto_john>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StudentPresentDto_john bean = new StudentPresentDto_john();
				bean.setLectSeriNum(rs.getInt(1));
				bean.setLectureName(rs.getString(2));
				bean.setMnum(rs.getInt(3));
				bean.setMid(rs.getString(4));
				bean.setMname(rs.getString(5));
				bean.setPresentRatio(rs.getDouble(6));
				list.add(bean);
			}
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}finally {
			destroy();
		}
		return list;
	}

}
