package com.cooklab.advertise.model;

import java.sql.Date;

public class AdvertiseVO implements java.io.Serializable {
	
	private Integer AdvertiseNo;
	private String AdvertiseName;
	private Date AdvertiseShelfTime;
	private Date AdvertiseOffsaleTime;
	private String AdvertiseImg;
	private String AdvertiseUrl;
	private Date createdTimestamp;
	
	public Integer getAdvertiseNo() {
		return AdvertiseNo;
	}
	public void setAdvertiseNo(Integer advertiseNo) {
		AdvertiseNo = advertiseNo;
	}
	public String getAdvertiseName() {
		return AdvertiseName;
	}
	public void setAdvertiseName(String advertiseName) {
		AdvertiseName = advertiseName;
	}
	public Date getAdvertiseShelfTime() {
		return AdvertiseShelfTime;
	}
	public void setAdvertiseShelfTime(Date advertiseShelfTime) {
		AdvertiseShelfTime = advertiseShelfTime;
	}
	public String getAdvertiseImg() {
		return AdvertiseImg;
	}
	public void setAdvertiseImg(String advertiseImg) {
		AdvertiseImg = advertiseImg;
	}
	public Date getAdvertiseOffsaleTime() {
		return AdvertiseOffsaleTime;
	}
	public void setAdvertiseOffsaleTime(Date advertiseOffsaleTime) {
		AdvertiseOffsaleTime = advertiseOffsaleTime;
	}
	public String getAdvertiseUrl() {
		return AdvertiseUrl;
	}
	public void setAdvertiseUrl(String advertiseUrl) {
		AdvertiseUrl = advertiseUrl;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
}
