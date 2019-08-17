package com.beat.Admin.Model;

import java.util.HashMap;

public class StudentAttendanceInsertDto {

	private int mnum;
	private String presentStatus;
	private HashMap<Integer, String> map;
	
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getPresentStatus() {
		return presentStatus;
	}
	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}
	public HashMap<Integer, String> getMap() {
		return map;
	}
	public void setMap(HashMap<Integer, String> map) {
		this.map = map;
	}

	
}
