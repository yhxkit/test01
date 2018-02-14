package com.beat.Admin.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.beat.util.LMSDao;

public class MemberDetailDao {
	PreparedStatement pstmt;
	ResultSet rs;

	public ArrayList<MemberDetailDto> memberListDetail(String mnum) {
		ArrayList<MemberDetailDto> list = new ArrayList<MemberDetailDto>();
		String sql = "select ";
		sql += "lmsMember.mname, lmsMember.mzen, lmsMember.mphone, ";
		sql += "lmsMember.mbirth, lmsMember.mmail, lmsMember.maddress, lmsMember.maddnum, ";
		sql += "Lecture_Room.LECTURENAME, Lecture_Room.TEACHERNAME, ";
		sql += "Lecture_Room.LECTURESTART, Lecture_Room.lectureEnd, Lecture_Room.STUDENTTOTAL,";
		sql += "Class_Grade.javaPoint, Class_Grade.webPoint, Class_Grade.DBPoint,";
		sql += "Class_Grade.pointAvr, Class_Grade.pointSum, ";
		sql += "Class_Information.presentDays, Class_Information.absentDays, ";
		sql += "CLASS_INFORMATION.EARLYHOME + CLASS_INFORMATION.LATETIMES, ";
		sql += "trunc(Class_Information.presentDays/20*100) ";
		sql += "from lmsMember, Lecture_Room, Class_Grade, CLASS_INFORMATION ";
		sql += "where lmsMember.mnum = '";
		sql += mnum;
		sql += "' ";
		sql += "and lmsMember.mnum = Class_Grade.mnum ";
		sql += "and Lecture_Room.LECTSERINUM = Class_Grade.LECTSERINUM ";
		sql += "and Class_Information.mnum = lmsMember.mnum";
		System.out.println(sql);
		ResultSet rs = null;

		int mzen = 0;
		String mzenText = null;


		try {

			String driverName = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@203.236.209.206:1521:XE";
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(url, "scott", "tiger");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDetailDto bean = new MemberDetailDto();
				bean.setMname(rs.getString(1));
				mzen = rs.getInt(2); // 성별
				if (mzen == 1)
					mzenText = "남";
				else if (mzen == 2)
					mzenText = "여";
				bean.setMzenText(mzenText);
				bean.setMphone(rs.getInt(3));
				bean.setMbirth(rs.getDate(4));
				bean.setMmail(rs.getString(5));
				bean.setMaddress(rs.getString(6));
				bean.setMaddnum(rs.getInt(7));
				bean.setLectureName(rs.getString(8));
				bean.setTeacherName(rs.getString(9));
				bean.setLectureStart(rs.getDate(10));
				bean.setLectureEnd(rs.getDate(11));
				bean.setStudentTotal(rs.getInt(12));
				bean.setJavaPoint(rs.getInt(13));
				bean.setWebPoint(rs.getInt(14));
				bean.setDbPoint(rs.getInt(15));
				bean.setPointAvr(rs.getDouble(16));
				bean.setPointSum(rs.getInt(17));
				bean.setPresentDays(rs.getInt(18));
				bean.setAbsentDays(rs.getInt(19));
				bean.setLateEarly(rs.getInt(20));
				bean.setPresentRatio(rs.getDouble(21));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return list;
	}

}
