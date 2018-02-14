package com.hb.major.model.entity;

import java.sql.Date;

public class NoticeVo {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeId;
	private String noticeNick;
	private Date noticeDay;
	
	public NoticeVo() {
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeNick() {
		return noticeNick;
	}

	public void setNoticeNick(String noticeNick) {
		this.noticeNick = noticeNick;
	}

	public Date getNoticeDay() {
		return noticeDay;
	}

	public void setNoticeDay(Date noticeDay) {
		this.noticeDay = noticeDay;
	}

	@Override
	public String toString() {
		return "NoticeVo [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeId=" + noticeId + ", noticeNick=" + noticeNick + ", noticeDay=" + noticeDay + "]";
	}

	public NoticeVo(int noticeNo, String noticeTitle, String noticeContent, String noticeId, String noticeNick,
			Date noticeDay) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeId = noticeId;
		this.noticeNick = noticeNick;
		this.noticeDay = noticeDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noticeContent == null) ? 0 : noticeContent.hashCode());
		result = prime * result + ((noticeId == null) ? 0 : noticeId.hashCode());
		result = prime * result + ((noticeNick == null) ? 0 : noticeNick.hashCode());
		result = prime * result + noticeNo;
		result = prime * result + ((noticeTitle == null) ? 0 : noticeTitle.hashCode());
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
		NoticeVo other = (NoticeVo) obj;
		if (noticeContent == null) {
			if (other.noticeContent != null)
				return false;
		} else if (!noticeContent.equals(other.noticeContent))
			return false;
		if (noticeId == null) {
			if (other.noticeId != null)
				return false;
		} else if (!noticeId.equals(other.noticeId))
			return false;
		if (noticeNick == null) {
			if (other.noticeNick != null)
				return false;
		} else if (!noticeNick.equals(other.noticeNick))
			return false;
		if (noticeNo != other.noticeNo)
			return false;
		if (noticeTitle == null) {
			if (other.noticeTitle != null)
				return false;
		} else if (!noticeTitle.equals(other.noticeTitle))
			return false;
		return true;
	}

}
