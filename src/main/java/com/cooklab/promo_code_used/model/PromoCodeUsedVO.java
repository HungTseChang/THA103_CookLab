package com.cooklab.promo_code_used.model;

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
@Table(name = "promo_code_used")
public class PromoCodeUsedVO implements Serializable{

	@Id
	@Column(name = "promo_code_used_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer promoCodeUsedNo;
	
	@Column(name = "member_id")
	private Integer memberId;
//	=================WCC==========================
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO  members;
	
//	================WCC======================
	@Column(name = "promo_code_no")
	private Integer promoCodeNo;
	
	@Column(name = "created_timestamp ", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	public Integer getPromoCodeUsedNo() {
		return promoCodeUsedNo;
	}

	public void setPromoCodeUsedNo(Integer promoCodeUsedNo) {
		this.promoCodeUsedNo = promoCodeUsedNo;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getPromoCodeNo() {
		return promoCodeNo;
	}

	public void setPromoCodeNo(Integer promoCodeNo) {
		this.promoCodeNo = promoCodeNo;
	}

	@Override
	public String toString() {
		return "PromoCodeUsedVO [promoCodeUsedNo=" + promoCodeUsedNo + ", memberId=" + memberId + ", promoCodeNo="
				+ promoCodeNo + ", createdTimestamp=" + createdTimestamp + "]";
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public PromoCodeUsedVO() {
		// TODO Auto-generated constructor stub
	}

}
