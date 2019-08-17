package com.beat.Admin.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.beat.util.LMSDao;

public class MemberListDao {
	PreparedStatement pstmt;
	ResultSet rs;

//	public MemberListDao() {
//		conn = super.conn;
//	}

	public ArrayList<MemberListDto> studentList(String selectColumn, String searchText) {
		

		ResultSet rs = null;

//		PreparedStatement pstmt = conn.createStatement();

//		rs = stmt.executeQuery(sql);
		
		
		ArrayList<MemberListDto> list = new ArrayList<MemberListDto>();
		
		String sql = "SELECT ";
		sql += "Lecture_Room.LECTSERINUM, "; // 강의번호
		sql += "Lecture_Room.LECTURENAME, "; // 강의명
		sql += "Class_Information.MNUM, "; // 회원번호
		sql += "lmsMember.MNAME, "; // 회원 이름
		sql += "lmsMember.MID, "; // 회원 아이디
		// sql +=
		// "trunc((1-Class_Information.ABSENTDAYS/Class_Information.PRESENTDAYS)*100) ";
		// // 출석률
		sql += "trunc(Class_Information.PRESENTDAYS/20*100)"; // 출석률
		sql += "from Lecture_Room, Class_Information, lmsMember ";
		sql += "where ";
		sql += "Class_Information.lectSeriNum = Lecture_Room.lectSeriNum ";
		sql += "and lmsMember.mnum = Class_Information.mnum";

		if (selectColumn=="!@#$")selectColumn=null;
		if(searchText=="!@#$")searchText=null;
		
		if (selectColumn != null && searchText != null) {
			System.out.println("searchText=" + searchText);
			if (selectColumn.equals("lectSeriNum"))
				sql += " and Lecture_Room.lectSeriNum like '%" + searchText + "%'";
			// 강의번호
			if (selectColumn.equals("lectureName"))
				sql += " and Lecture_Room.LECTURENAME like '%" + searchText + "%'";
			// 강의명
			if (selectColumn.equals("mnum"))
				sql += " and lmsMember.MNUM like '%" + searchText + "%'";
			// 회원번호
			if (selectColumn.equals("mname"))
				sql += " and lmsMember.MNAME like '%" + searchText + "%'";
			// 회원명
			if (selectColumn.equals("mid"))
				sql += " and lmsMember.MID like '%" + searchText + "%'";
			// 아이디
		}
		
		System.out.println(selectColumn);
		System.out.println(searchText);
		System.out.println(sql);

		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@203.236.209.206:1521:XE";
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url, "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberListDto bean = new MemberListDto();
				bean.setLectSeriNum(rs.getInt(1));
				bean.setLectureName(rs.getString(2));
				bean.setMnum(rs.getInt(3));
				bean.setMid(rs.getString(4));
				bean.setMname(rs.getString(5));
				bean.setPresentRatio(rs.getDouble(6));
				list.add(bean);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// 
			e.printStackTrace();
		}finally {
			
		}
		return list;
	}

}
