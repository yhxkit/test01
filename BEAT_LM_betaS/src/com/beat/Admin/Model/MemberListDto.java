package com.beat.Admin.Model;

public class MemberListDto {
	private int lectSeriNum;
	private String lectureName;
	private int mnum;
	private String mid;
	private String mname;
	private double presentRatio;
	public int getLectSeriNum() {
		return lectSeriNum;
	}
	public void setLectSeriNum(int lectSeriNum) {
		this.lectSeriNum = lectSeriNum;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public double getPresentRatio() {
		return presentRatio;
	}
	public void setPresentRatio(double presentRatio) {
		this.presentRatio = presentRatio;
	}
	
}
