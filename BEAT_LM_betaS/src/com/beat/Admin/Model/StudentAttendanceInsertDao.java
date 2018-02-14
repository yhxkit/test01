package com.beat.Admin.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class StudentAttendanceInsertDao {

	int mnum = 0;
	String driverName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@203.236.209.206:1521:XE";
	String sql = "";

	public void studentAttendanceInsert(ArrayList<Integer> list, String presentStatus) {
		sql = "";
		if (list.size() > 0) {
			sql = "update class_information set ";
			sql += presentStatus + "=" + presentStatus + "+1 ";
			sql += "where mnum = ";
			Iterator<Integer> it = list.iterator();
			sql += it.next();
			if (list.size() > 1) {
				while (it.hasNext()) {
					sql += " or mnum = ";
					sql += it.next();
				}
			}

			System.out.println(sql);
			Connection conn = null;
			Statement stmt = null;

			try {
				String driverName = "oracle.jdbc.driver.OracleDriver";
				String url = "jdbc:oracle:thin:@203.236.209.206:1521:XE";
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, "scott", "tiger");
				stmt = conn.createStatement();
				stmt.executeQuery(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		

	}

}
