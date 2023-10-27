package com.cooklab.member_order.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;
import com.cooklab.order_detail.model.OrderDetailVO;
import com.cooklab.promo_code.model.PromoCodeVO;

@Entity
@Table(name = "member_order")
public class MemberOrderVO implements Serializable{

	@Id
	@Column(name = "order_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderNo ;
	
	@Column(name = "member_id")
	private Integer memberId ;
	
//	優惠碼
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "promo_code_no", referencedColumnName = "promo_code_no", insertable = false, updatable = false)
	private PromoCodeVO  promoCode;
	
	//訂單明細
	@OneToMany(mappedBy = "memberOrder", cascade = CascadeType.ALL)
	private Set<OrderDetailVO> orderDetail;

//	會員
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO  members;
//	=============WCC================	
	@Column(name = "order_status", columnDefinition = "int")
	private byte orderStatus;  
//	==============WCC=============================
	@Column(name = "total_order_amount")
	private Integer totalOrderAmount;
	
	@Column(name = "checkout_amount")
    private Integer checkoutAmount;
	
	@Column(name = "promo_code_no ")
	private Integer promoCodeNo;
	
	@Column(name = "shipping_address")
	private String shippingAddress ;
	
	@Column(name = "created_timestamp ", insertable = false, updatable = false)
	private Timestamp createdTimestamp;
	
	
	
	
	
	
	public Set<OrderDetailVO> getOrderDetail() {
		if (orderDetail == null) {
            orderDetail = new HashSet<>(); // 懒加载，如果为null，初始化为一个新的HashSet
        }
        return orderDetail;
	}


	public void setOrderDetail(Set<OrderDetailVO> orderDetail) {
		this.orderDetail = orderDetail;
	}


	public PromoCodeVO getPromoCode() {
		return promoCode;
	}


	public void setPromoCode(PromoCodeVO promoCode) {
		this.promoCode = promoCode;
	}


	public MembersVO getMembers() {
		return members;
	}


	public void setMembers(MembersVO members) {
		this.members = members;
	}


	public void setOrderStatus(byte orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}


	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}


	public MemberOrderVO() {

	}


	public Integer getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public Byte getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Integer getTotalOrderAmount() {
		return totalOrderAmount;
	}


	public void setTotalOrderAmount(Integer totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}


	public Integer getCheckoutAmount() {
		return checkoutAmount;
	}


	public void setCheckoutAmount(Integer checkoutAmount) {
		this.checkoutAmount = checkoutAmount;
	}




	public Integer getPromoCodeNo() {
		return promoCodeNo;
	}


	public void setPromoCodeNo(Integer promoCodeNo) {
		this.promoCodeNo = promoCodeNo;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}




	


	

	

	
	
}
