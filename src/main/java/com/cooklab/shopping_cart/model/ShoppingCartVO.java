package com.cooklab.shopping_cart.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShoppingCartVO implements Serializable{

	private Integer shoppingCartNo;
	private Integer memberId ;
	private Integer productNo ;
	private Integer productQty;
	private Timestamp createdTimestamp;
	
	

	public Integer getShoppingCartNo() {
		return shoppingCartNo;
	}




	public void setShoppingCartNo(Integer shoppingCartNo) {
		this.shoppingCartNo = shoppingCartNo;
	}




	public Integer getMemberId() {
		return memberId;
	}




	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}




	public Integer getProductNo() {
		return productNo;
	}




	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}




	public Integer getProductQty() {
		return productQty;
	}




	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}



	@Override
	public String toString() {
		return "ShoppingCartVO [shoppingCartNo=" + shoppingCartNo + ", memberId=" + memberId + ", productNo="
				+ productNo + ", productQty=" + productQty + ", createdTimestamp=" + createdTimestamp + "]";
	}




	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}




	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}




	public ShoppingCartVO() {
		// TODO Auto-generated constructor stub
	}

}
