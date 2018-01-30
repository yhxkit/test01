package com.hb.major.model.entity;

public class UserVo {

	private String kakao_id;
	private String kakao_nick;
	private String kakao_profile_image;
	private String kakao_thumbnail_image;
	
	public UserVo() {
	}

	public String getKakao_id() {
		return kakao_id;
	}

	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}

	public String getKakao_nick() {
		return kakao_nick;
	}

	public void setKakao_nick(String kakao_nick) {
		this.kakao_nick = kakao_nick;
	}

	public String getKakao_profile_image() {
		return kakao_profile_image;
	}

	public void setKakao_profile_image(String kakao_profile_image) {
		this.kakao_profile_image = kakao_profile_image;
	}

	public String getKakao_thumbnail_image() {
		return kakao_thumbnail_image;
	}

	public void setKakao_thumbnail_image(String kakao_thumbnail_image) {
		this.kakao_thumbnail_image = kakao_thumbnail_image;
	}

	@Override
	public String toString() {
		return "UserVo [kakao_id=" + kakao_id + ", kakao_nick=" + kakao_nick + ", kakao_profile_image="
				+ kakao_profile_image + ", kakao_thumbnail_image=" + kakao_thumbnail_image + "]";
	}

	public UserVo(String kakao_id, String kakao_nick, String kakao_profile_image, String kakao_thumbnail_image) {
		super();
		this.kakao_id = kakao_id;
		this.kakao_nick = kakao_nick;
		this.kakao_profile_image = kakao_profile_image;
		this.kakao_thumbnail_image = kakao_thumbnail_image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kakao_id == null) ? 0 : kakao_id.hashCode());
		result = prime * result + ((kakao_nick == null) ? 0 : kakao_nick.hashCode());
		result = prime * result + ((kakao_profile_image == null) ? 0 : kakao_profile_image.hashCode());
		result = prime * result + ((kakao_thumbnail_image == null) ? 0 : kakao_thumbnail_image.hashCode());
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
		UserVo other = (UserVo) obj;
		if (kakao_id == null) {
			if (other.kakao_id != null)
				return false;
		} else if (!kakao_id.equals(other.kakao_id))
			return false;
		if (kakao_nick == null) {
			if (other.kakao_nick != null)
				return false;
		} else if (!kakao_nick.equals(other.kakao_nick))
			return false;
		if (kakao_profile_image == null) {
			if (other.kakao_profile_image != null)
				return false;
		} else if (!kakao_profile_image.equals(other.kakao_profile_image))
			return false;
		if (kakao_thumbnail_image == null) {
			if (other.kakao_thumbnail_image != null)
				return false;
		} else if (!kakao_thumbnail_image.equals(other.kakao_thumbnail_image))
			return false;
		return true;
	}

}