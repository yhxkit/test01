package com.hb.major.model.entity;

import java.sql.Date;

public class CommentVo {
	private int commNo;
	private String commId;
	private String commNick;
	private int bbsNo;
	private String commContent;
	private Date commDay;
	
	public CommentVo() {
	}

	public int getCommNo() {
		return commNo;
	}

	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}

	public String getCommId() {
		return commId;
	}

	public void setCommId(String commId) {
		this.commId = commId;
	}

	public String getCommNick() {
		return commNick;
	}

	public void setCommNick(String commNick) {
		this.commNick = commNick;
	}

	public int getBbsNo() {
		return bbsNo;
	}

	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public Date getCommDay() {
		return commDay;
	}

	public void setCommDay(Date commDay) {
		this.commDay = commDay;
	}

	@Override
	public String toString() {
		return "CommentVo [commNo=" + commNo + ", commId=" + commId + ", commNick=" + commNick + ", bbsNo=" + bbsNo
				+ ", commContent=" + commContent + ", commDay=" + commDay + "]";
	}

	public CommentVo(int commNo, String commId, String commNick, int bbsNo, String commContent, Date commDay) {
		super();
		this.commNo = commNo;
		this.commId = commId;
		this.commNick = commNick;
		this.bbsNo = bbsNo;
		this.commContent = commContent;
		this.commDay = commDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bbsNo;
		result = prime * result + ((commContent == null) ? 0 : commContent.hashCode());
		result = prime * result + ((commId == null) ? 0 : commId.hashCode());
		result = prime * result + ((commNick == null) ? 0 : commNick.hashCode());
		result = prime * result + commNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentVo other = (CommentVo) obj;
		if (bbsNo != other.bbsNo)
			return false;
		if (commContent == null) {
			if (other.commContent != null)
				return false;
		} else if (!commContent.equals(other.commContent))
			return false;
		if (commId == null) {
			if (other.commId != null)
				return false;
		} else if (!commId.equals(other.commId))
			return false;
		if (commNick == null) {
			if (other.commNick != null)
				return false;
		} else if (!commNick.equals(other.commNick))
			return false;
		if (commNo != other.commNo)
			return false;
		return true;
	}
	
}
