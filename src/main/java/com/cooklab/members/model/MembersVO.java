package com.cooklab.members.model;


import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "members")

public class MembersVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="member_id")
	private Integer memberId;
	
	@Column (name="member_account")
	private String memberAccount;
	
	@Column (name="member_password")
	private String memberPassword;
	
	@Column (name="member_introduce",nullable = true)
	private String memberIntroduce;
	
	@Column (name="member_cellphone")
	private String memberCellphone;
	
	@Column (name="member_mail")
	private String memberMail;
	
	@Column (name="member_date")
	private java.sql.Date  memberDate;
	
	@Column (name="member_address")
	private String memberAddress;
	
	@Column (name="member_country",nullable = true)
	private String memberCountry;
	
	@Column (name="member_status")
	private byte memberStatus;
	
	@Column (name="member_picture",nullable = true , columnDefinition = "longblob")
	private byte[] memberPicture;
	
	@Column (name="member_nickname")
	private String memberNickname;
	
	@Column (name="member_gender")
	private byte memberGender;
	
	@Column (name="created_timestamp",insertable = false)
	private Timestamp credcreatedTimestamp;
	
	@Column (name="last_edit_timestamp" ,insertable = false)
	private Timestamp lastEditTimestamp;

	
	public MembersVO() {
		
	}
	
	
	public MembersVO( String memberAccount, String memberPassword, String memberIntroduce,
			String memberCellphone, String memberMail, java.sql.Date memberDate, String memberAddress, String memberCountry,
			byte memberStatus, byte[] memberPicture, String memberNickname, byte memberGender) {
		super();
		
		this.memberAccount = memberAccount;
		this.memberPassword = memberPassword;
		this.memberIntroduce = memberIntroduce;
		this.memberCellphone = memberCellphone;
		this.memberMail = memberMail;
		this.memberDate = memberDate;
		this.memberAddress = memberAddress;
		this.memberCountry = memberCountry;
		this.memberStatus = memberStatus;
		this.memberPicture = memberPicture;
		this.memberNickname = memberNickname;
		this.memberGender = memberGender;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberIntroduce() {
		return memberIntroduce;
	}
	public void setMemberIntroduce(String memberIntroduce) {
		this.memberIntroduce = memberIntroduce;
	}
	public String getMemberCellphone() {
		return memberCellphone;
	}
	public void setMemberCellphone(String memberCellphone) {
		this.memberCellphone = memberCellphone;
	}
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	public java.sql.Date getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(java.sql.Date memberDate) {
		this.memberDate = memberDate;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberCountry() {
		return memberCountry;
	}
	public void setMemberCountry(String memberCountry) {
		this.memberCountry = memberCountry;
	}
	public byte getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(byte memberStatus) {
		this.memberStatus = memberStatus;
	}
	public byte[] getMemberPicture() {
		return memberPicture;
	}
	public void setMemberPicture(byte[] memberPicture) {
		this.memberPicture = memberPicture;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public byte getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(byte memberGender) {
		this.memberGender = memberGender;
	}
	
	
	
	

	public Timestamp getCredcreatedTimestamp() {
		return credcreatedTimestamp;
	}


	public void setCredcreatedTimestamp(Timestamp credcreatedTimestamp) {
		this.credcreatedTimestamp = credcreatedTimestamp;
	}


	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}


	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}


	@Override
	public String toString() {
		return "MembersVO [memberId=" + memberId + ", memberAccount=" + memberAccount + ", memberPassword="
				+ memberPassword + ", memberIntroduce=" + memberIntroduce + ", memberCellphone=" + memberCellphone
				+ ", memberMail=" + memberMail + ", memberDate=" + memberDate + ", memberAddress=" + memberAddress
				+ ", memberCountry=" + memberCountry + ", memberStatus=" + memberStatus + ", memberPicture="
				+ Arrays.toString(memberPicture) + ", memberNickname=" + memberNickname + ", memberGender="
				+ memberGender + ", credcreatedTimestamp=" + credcreatedTimestamp + ", lastEditTimestamp="
				+ lastEditTimestamp + "]";
	}
	
	
	
	
	
	

	
}
