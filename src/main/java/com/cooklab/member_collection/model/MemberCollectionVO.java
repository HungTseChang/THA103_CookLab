package com.cooklab.member_collection.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;
@Entity
@Table(name = "member_collection")
public class MemberCollectionVO implements Serializable {
	public MembersVO getMembers() {
		return members;
	}

	public void setMembers(MembersVO members) {
		this.members = members;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="member_collection_no",insertable = false, updatable = false)
	private Integer memberCollectionNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id_collectioned", referencedColumnName = "member_id")
	private MembersVO memberIdCollectioned;
//	@Column(name = "member_id")  
//	private Integer memberId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private MembersVO  members;
	
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;

	public Integer getMemberCollectionNo() {
		return memberCollectionNo;
	}

	public void setMemberCollectionNo(Integer memberCollectionNo) {
		this.memberCollectionNo = memberCollectionNo;
	}



//	public Integer getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(Integer memberId) {
//		this.memberId = memberId;
//	}

	public MembersVO getMemberIdCollectioned() {
		return memberIdCollectioned;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public void setMemberIdCollectioned(MembersVO memberIdCollectioned) {
		this.memberIdCollectioned = memberIdCollectioned;
	}

//	@Override
//	public String toString() {
//		return "MemberCollectionVO [memberCollectionNo=" + memberCollectionNo + ", memberIdCollectioned="
//				+ memberIdCollectioned + ", memberId=" + memberId + ", createdTimestamp=" + createdTimestamp + "]";
//	}

}
