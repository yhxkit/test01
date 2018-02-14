package com.beat.Lecture.model;

import java.sql.Date;

public class MyLectureDto {
	
	String lecname;
	int lecroom;
	String lecteacher;
	Date startdate;
	Date enddate;
	String redate;
	int stucnt;
	int maxcnt;
	
	int javascr;
	int webscr;
	int dbscr;
	double avr;
	
	int prst;
	int abst;
	int late;
	int earleav;
	double attendancy;
	
	
	public double getAttendancy() {
		return attendancy;
	}
	public void setAttendancy(double attendancy) {
		this.attendancy = attendancy;
	}
	public String getLecname() {
		return lecname;
	}
	public void setLecname(String lecname) {
		this.lecname = lecname;
	}
	public int getLecroom() {
		return lecroom;
	}
	public void setLecroom(int lecroom) {
		this.lecroom = lecroom;
	}
	public String getLecteacher() {
		return lecteacher;
	}
	public void setLecteacher(String lecteacher) {
		this.lecteacher = lecteacher;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getRedate() {
		redate = startdate +"~"+enddate;
		return redate;
	}

	public int getStucnt() {
		return stucnt;
	}
	public void setStucnt(int stucnt) {
		this.stucnt = stucnt;
	}
	public int getMaxcnt() {
		return maxcnt;
	}
	public void setMaxcnt(int maxcnt) {
		this.maxcnt = maxcnt;
	}
	public int getJavascr() {
		return javascr;
	}
	public void setJavascr(int javascr) {
		this.javascr = javascr;
	}
	public int getWebscr() {
		return webscr;
	}
	public void setWebscr(int webscr) {
		this.webscr = webscr;
	}
	public int getDbscr() {
		return dbscr;
	}
	public void setDbscr(int dbscr) {
		this.dbscr = dbscr;
	}
	public double getAvr() {
		return avr;
	}
	public void setAvr(double avr) {
		this.avr = avr;
	}
	public int getPrst() {
		return prst;
	}
	public void setPrst(int prst) {
		this.prst = prst;
	}
	public int getAbst() {
		return abst;
	}
	public void setAbst(int abst) {
		this.abst = abst;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getEarleav() {
		return earleav;
	}
	public void setEarleav(int earleav) {
		this.earleav = earleav;
	}
	
	
}
