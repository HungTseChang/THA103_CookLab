package com.cooklab.promo_code.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromoCodeVO implements Serializable{

	private Integer promoCodeNo;
	private String promoCodeSerialNumber;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer percentageDiscountAmount;
	private Integer fixedDiscountAmount;
	private Integer usagesAllowed;
	private Integer minimumConsumption;
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

	public Integer getPercentageDiscountAmount() {
		return percentageDiscountAmount;
	}

	public void setPercentageDiscountAmount(Integer percentageDiscountAmount) {
		this.percentageDiscountAmount = percentageDiscountAmount;
	}

	public Integer getFixedDiscountAmount() {
		return fixedDiscountAmount;
	}

	public void setFixedDiscountAmount(Integer fixedDiscountAmount) {
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
