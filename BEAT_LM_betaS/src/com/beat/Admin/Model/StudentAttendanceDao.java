package com.beat.Admin.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.beat.util.LMSDao;

public class StudentAttendanceDao extends LMSDao {
	PreparedStatement pstmt;
	ResultSet rs;

	// public MemberListDao() {
	// conn = super.conn;
	// }

	public ArrayList<StudentAttendanceDto> studentList(String selectColumn, String searchText) {

		ResultSet rs = null;

		ArrayList<StudentAttendanceDto> list = new ArrayList<StudentAttendanceDto>();

		String sql = "select ";
		sql += "lmsMember.mnum, lmsMember.mname, Lecture_Room.roomNumber, Lecture_Room.lectureName "; // ��ȣ, �̸�, ���ǽ�,
																										// ���Ǹ�
		sql += "from ";
		sql += "LMSMEMBER, LECTURE_ROOM, Class_Information ";
		sql += "where ";
		sql += "Class_Information.mnum = lmsMember.mnum ";
		sql += "and LECTURE_ROOM.LECTSERINUM = Class_Information.LECTSERINUM";

		if (selectColumn == "!@#$")
			selectColumn = null;
		if (searchText == "!@#$")
			searchText = null;

		if (selectColumn == "!@#$")
			selectColumn = null;
		if (searchText == "!@#$")
			searchText = null;

		if (selectColumn != null && searchText != null) {
			System.out.println("searchText=" + searchText);
			System.out.println("selectColumn=" + selectColumn);
			if (selectColumn.equals("mnum"))
				sql += " and lmsMember.MNUM like '%" + searchText + "%'";
			// ȸ����ȣ
			if (selectColumn.equals("mname"))
				sql += " and lmsMember.MNAME like '%" + searchText + "%'";
			// ȸ����
			if (selectColumn.equals("roomNumber"))
				sql += " and LECTURE_ROOM.ROOMNUMBER like '%" + searchText + "%'";
			if (selectColumn.equals("lectureName"))
				sql += " and Lecture_Room.LECTURENAME like '%" + searchText + "%'";
			// ���Ǹ�
			if (selectColumn.equals("mid"))
				sql += " and lmsMember.MID like '%" + searchText + "%'";
			// ���̵�
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
			while (rs.next()) {
				StudentAttendanceDto bean = new StudentAttendanceDto();
				bean.setMnum(rs.getInt(1));
				bean.setMname(rs.getString(2));
				bean.setRoomNumber(rs.getString(3));
				bean.setLectureName(rs.getString(4));
				list.add(bean);
			}
		} catch (SQLException | ClassNotFoundException e) {
			//
			e.printStackTrace();
		} finally {
			destroy();
		}
		return list;
	}

}

// <%
// String mnum = null;
// while (rs.next()) {
// out.print("<tr>");
// mnum = rs.getString(1);
// out.print("<td>" + mnum + "</td>"); // ��ȣ
// out.print("<td>" + rs.getString(2) + "</td>"); // �̸�
// out.print("<td>" + rs.getString(3) + "</td>"); // ���Ǹ�
// out.print("<td>���� �ؽ�Ʈ</td>");
// out.print("<td><input type='radio' value='presentDays'
// name='"+mnum+"'/></td>"); // �⼮
// out.print("<td><input type='radio' value='lateTimes'
// name='"+mnum+"'/></td>"); // ����
// out.print("<td><input type='radio' value='earlyHome'
// name='"+mnum+"'/></td>"); // ����
// out.print("<td><input type='radio' value='absentDays' name='"+mnum+"'
// checked='checked'/></td>"); // �Ἦ
// out.print("</tr>");
// }
// %>