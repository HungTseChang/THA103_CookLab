package com.cooklab.promo_code.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cooklab.member_order.model.MemberOrderVO;


@Entity
@Table(name = "promo_code")
public class PromoCodeVO implements Serializable{

	
	// 會員訂單關聯
	@OneToMany(mappedBy = "promoCode", cascade = CascadeType.ALL)
	private Set<MemberOrderVO> memberOrder;
	
	
	@Id
	@Column(name = "promo_code_no" ,updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer promoCodeNo;
	
	@Column(name = "promo_code_serial_number")
	private String promoCodeSerialNumber;
	
	@Column(name = "start_time")
	private Timestamp startTime;
	
	@Column(name = "end_time")
	private Timestamp endTime;
	
	@Column(name = "percentage_discount_amount")
	private BigDecimal percentageDiscountAmount;
	
	@Column(name = "fixed_discount_amount")
	private BigDecimal fixedDiscountAmount;
	
	@Column(name = "usages_allowed")
	private Integer usagesAllowed;
	
	@Column(name = "minimum_consumption")
	private Integer minimumConsumption;
	
	@Column(name = "created_timestamp ", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	public Integer getPromoCodeNo() {
		return promoCodeNo;
	}

	public void setPromoCodeNo(Integer promoCodeNo) {
		this.promoCodeNo = promoCodeNo;
	}

	public String getPromoCodeSerialNumber() {
		return promoCodeSerialNumber;
	}

	public void setPromoCodeSerialNumber(String promoCodeSerialNumber) {
		this.promoCodeSerialNumber = promoCodeSerialNumber;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getPercentageDiscountAmount() {
		return percentageDiscountAmount;
	}

	public void setPercentageDiscountAmount(BigDecimal percentageDiscountAmount) {
		this.percentageDiscountAmount = percentageDiscountAmount;
	}

	public BigDecimal getFixedDiscountAmount() {
		return fixedDiscountAmount;
	}

	public void setFixedDiscountAmount(BigDecimal fixedDiscountAmount) {
		this.fixedDiscountAmount = fixedDiscountAmount;
	}

	public Integer getUsagesAllowed() {
		return usagesAllowed;
	}

	public void setUsagesAllowed(Integer usagesAllowed) {
		this.usagesAllowed = usagesAllowed;
	}

	public Integer getMinimumConsumption() {
		return minimumConsumption;
	}

	public void setMinimumConsumption(Integer minimumConsumption) {
		this.minimumConsumption = minimumConsumption;
	}

	@Override
	public String toString() {
		return "PromoCodeVO [promoCodeNo=" + promoCodeNo + ", promoCodeSerialNumber=" + promoCodeSerialNumber
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", percentageDiscountAmount="
				+ percentageDiscountAmount + ", fixedDiscountAmount=" + fixedDiscountAmount + ", usagesAllowed="
				+ usagesAllowed + ", minimumConsumption=" + minimumConsumption + ", createdTimestamp="
				+ createdTimestamp + "]";
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public PromoCodeVO() {
		// TODO Auto-generated constructor stub
	}

}
