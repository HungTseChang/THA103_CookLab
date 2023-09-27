package com.cooklab.promo_code_used.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromoCodeUsedVO implements Serializable{

	private Integer promoCodeUsedNo;
	private Integer memberId;
	private Integer promoCodeNo;
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
