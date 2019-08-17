package com.beat.Admin.Model;

import java.sql.Date;

public class MemberDetailDto {

	String mname;
	int mzen;
	String mzenText;
	int mphone;
	Date mbirth;
	String mmail;
	String maddress;
	int maddnum;
	String lectureName;
	String teacherName;
	Date lectureStart;
	Date lectureEnd;
	int studentTotal;
	int javaPoint;
	int webPoint;
	int dbPoint;
	double pointAvr;
	int pointSum;
	int presentDays;
	int absentDays;
	int lateEarly;
	double presentRatio;

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getMzen() {
		return mzen;
	}

	public void setMzen(int mzen) {
		this.mzen = mzen;
	}

	public String getMzenText() {
		return mzenText;
	}

	public void setMzenText(String mzenText) {
		this.mzenText = mzenText;
	}

	public int getMphone() {
		return mphone;
	}

	public void setMphone(int mphone) {
		this.mphone = mphone;
	}

	public Date getMbirth() {
		return mbirth;
	}

	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}

	public String getMmail() {
		return mmail;
	}

	public void setMmail(String mmail) {
		this.mmail = mmail;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}

	public int getMaddnum() {
		return maddnum;
	}

	public void setMaddnum(int maddnum) {
		this.maddnum = maddnum;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getLectureStart() {
		return lectureStart;
	}

	public void setLectureStart(Date lectureStart) {
		this.lectureStart = lectureStart;
	}

	public Date getLectureEnd() {
		return lectureEnd;
	}

	public void setLectureEnd(Date lectureEnd) {
		this.lectureEnd = lectureEnd;
	}

	public int getStudentTotal() {
		return studentTotal;
	}

	public void setStudentTotal(int studentTotal) {
		this.studentTotal = studentTotal;
	}

	public int getJavaPoint() {
		return javaPoint;
	}

	public void setJavaPoint(int javaPoint) {
		this.javaPoint = javaPoint;
	}

	public int getWebPoint() {
		return webPoint;
	}

	public void setWebPoint(int webPoint) {
		this.webPoint = webPoint;
	}

	public int getDbPoint() {
		return dbPoint;
	}

	public void setDbPoint(int dbPoint) {
		this.dbPoint = dbPoint;
	}

	public double getPointAvr() {
		return pointAvr;
	}

	public void setPointAvr(double pointAvr) {
		this.pointAvr = pointAvr;
	}

	public int getPointSum() {
		return pointSum;
	}

	public void setPointSum(int pointSum) {
		this.pointSum = pointSum;
	}

	public int getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(int presentDays) {
		this.presentDays = presentDays;
	}

	public int getAbsentDays() {
		return absentDays;
	}

	public void setAbsentDays(int absentDays) {
		this.absentDays = absentDays;
	}

	public int getLateEarly() {
		return lateEarly;
	}

	public void setLateEarly(int lateEarly) {
		this.lateEarly = lateEarly;
	}

	public double getPresentRatio() {
		return presentRatio;
	}

	public void setPresentRatio(double presentRatio) {
		this.presentRatio = presentRatio;
	}

}
