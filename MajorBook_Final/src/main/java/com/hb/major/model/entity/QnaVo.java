package com.hb.major.model.entity;

import java.sql.Date;

public class QnaVo {

	private int qnaNo;
	private String qnaId;
	private String qnaNick;
	private String qnaTitle;
	private String qnaContent;
	private Date qnaDay;
	private String qnaStatus;
	
	public QnaVo() {
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaId() {
		return qnaId;
	}

	public void setQnaId(String qnaId) {
		this.qnaId = qnaId;
	}

	public String getQnaNick() {
		return qnaNick;
	}

	public void setQnaNick(String qnaNick) {
		this.qnaNick = qnaNick;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public Date getQnaDay() {
		return qnaDay;
	}

	public void setQnaDay(Date qnaDay) {
		this.qnaDay = qnaDay;
	}

	public String getQnaStatus() {
		return qnaStatus;
	}

	public void setQnaStatus(String qnaStatus) {
		this.qnaStatus = qnaStatus;
	}

	@Override
	public String toString() {
		return "QnaVo [qnaNo=" + qnaNo + ", qnaId=" + qnaId + ", qnaNick=" + qnaNick + ", qnaTitle=" + qnaTitle
				+ ", qnaContent=" + qnaContent + ", qnaDay=" + qnaDay + ", qnaStatus=" + qnaStatus + "]";
	}

	public QnaVo(int qnaNo, String qnaId, String qnaNick, String qnaTitle, String qnaContent, Date qnaDay,
			String qnaStatus) {
		super();
		this.qnaNo = qnaNo;
		this.qnaId = qnaId;
		this.qnaNick = qnaNick;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaDay = qnaDay;
		this.qnaStatus = qnaStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qnaContent == null) ? 0 : qnaContent.hashCode());
		result = prime * result + ((qnaId == null) ? 0 : qnaId.hashCode());
		result = prime * result + ((qnaNick == null) ? 0 : qnaNick.hashCode());
		result = prime * result + qnaNo;
		result = prime * result + ((qnaStatus == null) ? 0 : qnaStatus.hashCode());
		result = prime * result + ((qnaTitle == null) ? 0 : qnaTitle.hashCode());
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
		QnaVo other = (QnaVo) obj;
		if (qnaContent == null) {
			if (other.qnaContent != null)
				return false;
		} else if (!qnaContent.equals(other.qnaContent))
			return false;
		if (qnaId == null) {
			if (other.qnaId != null)
				return false;
		} else if (!qnaId.equals(other.qnaId))
			return false;
		if (qnaNick == null) {
			if (other.qnaNick != null)
				return false;
		} else if (!qnaNick.equals(other.qnaNick))
			return false;
		if (qnaNo != other.qnaNo)
			return false;
		if (qnaStatus == null) {
			if (other.qnaStatus != null)
				return false;
		} else if (!qnaStatus.equals(other.qnaStatus))
			return false;
		if (qnaTitle == null) {
			if (other.qnaTitle != null)
				return false;
		} else if (!qnaTitle.equals(other.qnaTitle))
			return false;
		return true;
	}
	
}
