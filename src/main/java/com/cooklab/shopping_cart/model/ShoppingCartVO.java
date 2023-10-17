package com.cooklab.shopping_cart.model;

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
@Table(name="shopping_cart") 
public class ShoppingCartVO implements Serializable{
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "shopping_cart_no" , insertable = false, updatable = false)
	private Integer shoppingCartNo;
	
	
	@Column(name = "member_id")
	private Integer memberId ;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO members;
	
	@Column(name = "product_no")
	private Integer productNo ;
	@Column(name = "product_qty")
	private Integer productQty;
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
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
