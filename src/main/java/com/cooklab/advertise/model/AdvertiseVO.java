package com.cooklab.advertise.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "advertise")

public class AdvertiseVO implements java.io.Serializable {

	@Id
	@Column(name = "advertise_no", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer AdvertiseNo;
	@Column(name = "advertise_name")
	private String AdvertiseName;
	@Column(name = "advertise_shelf_time")
	private Timestamp AdvertiseShelfTime;
	@Column(name = "advertise_offsale_time")
	private Timestamp AdvertiseOffsaleTime;
	@Column(name = "advertise_img",columnDefinition="longblob")
	private byte[] AdvertiseImg;
	@Column(name = "advertise_url",columnDefinition="text")
	private String AdvertiseUrl;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

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

	public Timestamp getAdvertiseShelfTime() {
		return AdvertiseShelfTime;
	}

	public void setAdvertiseShelfTime(Timestamp advertiseShelfTime) {
		AdvertiseShelfTime = advertiseShelfTime;
	}

	public byte[] getAdvertiseImg() {
		return AdvertiseImg;
	}

	public void setAdvertiseImg(byte[] AdvertiseImg) {
		this.AdvertiseImg = AdvertiseImg;
	}

	public Timestamp getAdvertiseOffsaleTime() {
		return AdvertiseOffsaleTime;
	}

	public void setAdvertiseOffsaleTime(Timestamp advertiseOffsaleTime) {
		AdvertiseOffsaleTime = advertiseOffsaleTime;
	}

	public String getAdvertiseUrl() {
		return AdvertiseUrl;
	}

	public void setAdvertiseUrl(String advertiseUrl) {
		AdvertiseUrl = advertiseUrl;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	

	public AdvertiseVO(Integer advertiseNo, String advertiseName, Timestamp advertiseShelfTime,
			Timestamp advertiseOffsaleTime, byte[] advertiseImg, String advertiseUrl, Timestamp createdTimestamp) {
		super();
		AdvertiseNo = advertiseNo;
		AdvertiseName = advertiseName;
		AdvertiseShelfTime = advertiseShelfTime;
		AdvertiseOffsaleTime = advertiseOffsaleTime;
		AdvertiseImg = advertiseImg;
		AdvertiseUrl = advertiseUrl;
		this.createdTimestamp = createdTimestamp;
	}

	public AdvertiseVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "advertise [advertise_no=" + AdvertiseNo + ", advertise_name=" + AdvertiseName
				+ ",advertise_shelf_time=" + AdvertiseShelfTime + ",advertise_offsale_time=" + AdvertiseOffsaleTime
				+ ",advertise_img=" + AdvertiseImg + ",advertise_url=" + AdvertiseUrl + ",created_timestamp="
				+ createdTimestamp + "]";
	}

}
