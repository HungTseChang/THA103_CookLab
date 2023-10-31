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
import com.cooklab.promo_code.model.PromoCodeVO;


@Entity
@Table(name = "promo_code_used")
public class PromoCodeUsedVO implements Serializable{

	@Id
	@Column(name = "promo_code_used_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer promoCodeUsedNo;
	
	//會員關聯
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO  members;
	
	//優惠碼關聯
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promo_code_no", referencedColumnName = "promo_code_no", insertable = false, updatable = false)
	private PromoCodeVO  promoCode;
	

	
	@Column(name = "created_timestamp ", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	
	
	
	public MembersVO getMembers() {
		return members;
	}

	public void setMembers(MembersVO members) {
		this.members = members;
	}

	public PromoCodeVO getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(PromoCodeVO promoCode) {
		this.promoCode = promoCode;
	}

	public Integer getPromoCodeUsedNo() {
		return promoCodeUsedNo;
	}

	public void setPromoCodeUsedNo(Integer promoCodeUsedNo) {
		this.promoCodeUsedNo = promoCodeUsedNo;
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
