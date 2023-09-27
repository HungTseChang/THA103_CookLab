package com.cooklab.memeber_collection.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MemberCollectionVO implements Serializable {

	private Integer memberCollectionNo;
	private Integer memberIdCollectioned;
	private Integer memberId;
	private Timestamp createdTimestamp;

	public Integer getMemberCollectionNo() {
		return memberCollectionNo;
	}

	public void setMemberCollectionNo(Integer memberCollectionNo) {
		this.memberCollectionNo = memberCollectionNo;
	}

	public Integer getMemberIdCollectioned() {
		return memberIdCollectioned;
	}

	public void setMemberIdCollectioned(Integer memberIdCollectioned) {
		this.memberIdCollectioned = memberIdCollectioned;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "MemberCollectionVO [memberCollectionNo=" + memberCollectionNo + ", memberIdCollectioned="
				+ memberIdCollectioned + ", memberId=" + memberId + ", createdTimestamp=" + createdTimestamp + "]";
	}

}
