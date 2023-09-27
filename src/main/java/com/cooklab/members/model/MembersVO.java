package com.cooklab.members.model;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;


public class MembersVO implements java.io.Serializable{
	private Integer memberId;
	private String memberAccount;
	private String memberPassword;
	private String memberIntroduce;
	private String memberCellphone;
	private String memberMail;
	private Date memberDate;
	private String memberAddress;
	private String memberCountry;
	private Integer memberStatus;
	private byte[] memberPicture;
	private String memberNickname;
	private Integer memberGender;
	private Timestamp credcreatedTimestamp;
	private Timestamp lastEditTimestamp;

	
	public MembersVO() {
		
	}
	
	
	public MembersVO( String memberAccount, String memberPassword, String memberIntroduce,
			String memberCellphone, String memberMail, Date memberDate, String memberAddress, String memberCountry,
			Integer memberStatus, byte[] memberPicture, String memberNickname, Integer memberGender) {
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
	public Date getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(Date memberDate) {
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
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
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
	public Integer getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(Integer memberGender) {
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
