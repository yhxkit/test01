package com.hb.major.model.entity;

import java.sql.Date;

public class QnaCommentVo {
	
	private int qnaCommNo;
	private String qnaCommId;
	private String qnaCommNick;
	private int qnaListNo;
	private String qnaCommContent;
	private Date qnaCommDay;
	
	public QnaCommentVo() {
	}

	public int getQnaCommNo() {
		return qnaCommNo;
	}

	public void setQnaCommNo(int qnaCommNo) {
		this.qnaCommNo = qnaCommNo;
	}

	public String getQnaCommId() {
		return qnaCommId;
	}

	public void setQnaCommId(String qnaCommId) {
		this.qnaCommId = qnaCommId;
	}

	public String getQnaCommNick() {
		return qnaCommNick;
	}

	public void setQnaCommNick(String qnaCommNick) {
		this.qnaCommNick = qnaCommNick;
	}

	public int getQnaListNo() {
		return qnaListNo;
	}

	public void setQnaListNo(int qnaListNo) {
		this.qnaListNo = qnaListNo;
	}

	public String getQnaCommContent() {
		return qnaCommContent;
	}

	public void setQnaCommContent(String qnaCommContent) {
		this.qnaCommContent = qnaCommContent;
	}

	public Date getQnaCommDay() {
		return qnaCommDay;
	}

	public void setQnaCommDay(Date qnaCommDay) {
		this.qnaCommDay = qnaCommDay;
	}

	@Override
	public String toString() {
		return "QnaCommentVo [qnaCommNo=" + qnaCommNo + ", qnaCommId=" + qnaCommId + ", qnaCommNick=" + qnaCommNick
				+ ", qnaListNo=" + qnaListNo + ", qnaCommContent=" + qnaCommContent + ", qnaCommDay=" + qnaCommDay
				+ "]";
	}

	public QnaCommentVo(int qnaCommNo, String qnaCommId, String qnaCommNick, int qnaListNo, String qnaCommContent,
			Date qnaCommDay) {
		super();
		this.qnaCommNo = qnaCommNo;
		this.qnaCommId = qnaCommId;
		this.qnaCommNick = qnaCommNick;
		this.qnaListNo = qnaListNo;
		this.qnaCommContent = qnaCommContent;
		this.qnaCommDay = qnaCommDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qnaCommContent == null) ? 0 : qnaCommContent.hashCode());
		result = prime * result + ((qnaCommId == null) ? 0 : qnaCommId.hashCode());
		result = prime * result + ((qnaCommNick == null) ? 0 : qnaCommNick.hashCode());
		result = prime * result + qnaCommNo;
		result = prime * result + qnaListNo;
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
		QnaCommentVo other = (QnaCommentVo) obj;
		if (qnaCommContent == null) {
			if (other.qnaCommContent != null)
				return false;
		} else if (!qnaCommContent.equals(other.qnaCommContent))
			return false;
		if (qnaCommId == null) {
			if (other.qnaCommId != null)
				return false;
		} else if (!qnaCommId.equals(other.qnaCommId))
			return false;
		if (qnaCommNick == null) {
			if (other.qnaCommNick != null)
				return false;
		} else if (!qnaCommNick.equals(other.qnaCommNick))
			return false;
		if (qnaCommNo != other.qnaCommNo)
			return false;
		if (qnaListNo != other.qnaListNo)
			return false;
		return true;
	}
	
}
