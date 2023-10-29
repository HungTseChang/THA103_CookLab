package com.cooklab.members.model;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.article_reaction.model.ArticleReactionVO;
import com.cooklab.article_report.model.ArticleReportVO;
import com.cooklab.article_sub.model.ArticleSubVO;
import com.cooklab.article_sub_report.model.ArticleSubReportVO;
import com.cooklab.member_collection.model.MemberCollectionVO;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.member_collection.model.MemberCollectionVO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.promo_code_used.model.PromoCodeUsedVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.recipe_comments_report.model.RecipeCommentsReportVO;
import com.cooklab.recipe_reaction.model.RecipeReactionVO;
import com.cooklab.recipe_report.model.RecipeReportVO;
import com.cooklab.recipe_step.model.RecipeStepVO;
import com.cooklab.shopping_cart.model.ShoppingCartVO;

@Entity
@Table(name = "members")

public class MembersVO  implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="member_id",insertable = false, updatable = false)
	private Integer memberId;
	
//	private MembersVO members;		//會員編號(FK)
	//●●● 請組員參考這部分去處理 ManyToOne ●●● =====================
	
//	//●食譜反應 recipe_reaction
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<RecipeReactionVO> recipeReactionS;
	
//	//●食譜留言檢舉 recipe_comments_report
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<RecipeCommentsReportVO> recipeCommentsReportS;
	
//	●食譜檢舉 recipe_report
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<RecipeReportVO> recipeReportS;
	
	//●食譜收藏 recipe_collection
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<RecipeCollectionVO> recipeCollectionS;
	//●食譜 recipe
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<RecipeVO> recipeS;
	
	//●購物車明細 shopping_cart
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<ShoppingCartVO> shoppingCartS;
	
	//●會員訂單 member_order
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<MemberOrderVO> memberOrderS;
	
	//●優惠碼使用情況 promo_code_used
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<PromoCodeUsedVO> promoCodeUsedS;
	
	//●通知中心 notify_center
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<NotifyCenterVO> notifyCenterS;
	
	//●會員關注 memeber_collection
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<MemberCollectionVO> memberCollectionS;
	
	//●討論區回文檢舉 article_sub_report
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<ArticleSubReportVO> articleSubReportS;
	
	//●討論區文章收藏 article_collection
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<ArticleCollectionVO> articleCollectionS;
	
	//●討論區文章反映 article_reaction
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<ArticleReactionVO> articleReactionS;
	
	//●討論區文章 article  
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<ArticleVO> articleS;
	
	//●討論區回文 article_sub 
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<ArticleSubVO> articleSubS;
	



	//●討論區檢舉 article_report
	@OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
	@OrderBy("member_id asc")
	private Set<ArticleReportVO> articleReportS;
	
	//==================================================================================================
	@Column (name="member_account")
	private String memberAccount;
	
	@Column (name="member_password")
	private String memberPassword;
	
	@Column (name="member_introduce",nullable = true)
	private String memberIntroduce;
	
	@Column (name="member_cellphone")
	private String memberCellphone;
	
	@Column (name="member_mail")
	private String memberMail;
	
	@Column (name="member_date")
	private java.sql.Date  memberDate;
	
	@Column (name="member_address")
	private String memberAddress;
	
	@Column (name="member_country",nullable = true)
	private String memberCountry;
	
	@Column (name="member_status")
	private byte memberStatus;
	
	@Column (name="member_picture",nullable = true , columnDefinition = "longblob")
	private byte[] memberPicture;
	
	@Column (name="member_nickname")
	private String memberNickname;
	
	@Column (name="member_gender")
	private byte memberGender;
	
	@Column (name="created_timestamp",insertable = false)
	private Timestamp credcreatedTimestamp;
	
	@Column (name="last_edit_timestamp" ,insertable = false)
	private Timestamp lastEditTimestamp;

	
	public MembersVO() {
		
	}
	
	
	public MembersVO( String memberAccount, String memberPassword, String memberIntroduce,
			String memberCellphone, String memberMail, java.sql.Date memberDate, String memberAddress, String memberCountry,
			byte memberStatus, byte[] memberPicture, String memberNickname, byte memberGender) {
		super();
		
		this.memberAccount = memberAccount;
		this.memberPassword = memberPassword;
		this.memberIntroduce = memberIntroduce;
		this.memberCellphone = memberCellphone;
		this.memberMail = memberMail;
		this.memberDate = memberDate;
		this.memberAddress = memberAddress;
		this.memberCountry = memberCountry;
		this.memberStatus = memberStatus;
		this.memberPicture = memberPicture;
		this.memberNickname = memberNickname;
		this.memberGender = memberGender;
	}
	//GET 與 SET 方法
	
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberIntroduce() {
		return memberIntroduce;
	}
	public void setMemberIntroduce(String memberIntroduce) {
		this.memberIntroduce = memberIntroduce;
	}
	public String getMemberCellphone() {
		return memberCellphone;
	}
	public void setMemberCellphone(String memberCellphone) {
		this.memberCellphone = memberCellphone;
	}
	public String getMemberMail() {
		return memberMail;
	}
	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}
	public java.sql.Date getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(java.sql.Date memberDate) {
		this.memberDate = memberDate;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberCountry() {
		return memberCountry;
	}
	public void setMemberCountry(String memberCountry) {
		this.memberCountry = memberCountry;
	}
	public byte getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(byte memberStatus) {
		this.memberStatus = memberStatus;
	}
	public byte[] getMemberPicture() {
		return memberPicture;
	}
	public void setMemberPicture(byte[] memberPicture) {
		this.memberPicture = memberPicture;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public byte getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(byte memberGender) {
		this.memberGender = memberGender;
	}
	
	public Timestamp getCredcreatedTimestamp() {
		return credcreatedTimestamp;
	}

	public void setCredcreatedTimestamp(Timestamp credcreatedTimestamp) {
		this.credcreatedTimestamp = credcreatedTimestamp;
	}

	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}

	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}


	public Set<RecipeReactionVO> getRecipeReactionS() {
		return recipeReactionS;
	}


	public void setRecipeReactionS(Set<RecipeReactionVO> recipeReactionS) {
		this.recipeReactionS = recipeReactionS;
	}


	public Set<RecipeCommentsReportVO> getRecipeCommentsReportS() {
		return recipeCommentsReportS;
	}


	public void setRecipeCommentsReportS(Set<RecipeCommentsReportVO> recipeCommentsReportS) {
		this.recipeCommentsReportS = recipeCommentsReportS;
	}


//	public Set<RecipeReportVO> getRecipeReportS() {
//		return recipeReportS;
//	}
//
//
//	public void setRecipeReportS(Set<RecipeReportVO> recipeReportS) {
//		this.recipeReportS = recipeReportS;
//	}


	public Set<RecipeCollectionVO> getRecipeCollectionS() {
		return recipeCollectionS;
	}


	public void setRecipeCollectionS(Set<RecipeCollectionVO> recipeCollectionS) {
		this.recipeCollectionS = recipeCollectionS;
	}


	public Set<RecipeVO> getRecipeS() {
		return recipeS;
	}


	public void setRecipeS(Set<RecipeVO> recipeS) {
		this.recipeS = recipeS;
	}


	public Set<ShoppingCartVO> getShoppingCartS() {
		return shoppingCartS;
	}


	public void setShoppingCartS(Set<ShoppingCartVO> shoppingCartS) {
		this.shoppingCartS = shoppingCartS;
	}


	public Set<MemberOrderVO> getMemberOrderS() {
		return memberOrderS;
	}


	public void setMemberOrderS(Set<MemberOrderVO> memberOrderS) {
		this.memberOrderS = memberOrderS;
	}


	public Set<PromoCodeUsedVO> getPromoCodeUsedS() {
		return promoCodeUsedS;
	}


	public void setPromoCodeUsedS(Set<PromoCodeUsedVO> promoCodeUsedS) {
		this.promoCodeUsedS = promoCodeUsedS;
	}


	public Set<NotifyCenterVO> getNotifyCenterS() {
		return notifyCenterS;
	}


	public void setNotifyCenterS(Set<NotifyCenterVO> notifyCenterS) {
		this.notifyCenterS = notifyCenterS;
	}


	public Set<MemberCollectionVO> getMemberCollectionS() {
		return memberCollectionS;
	}


	public void setMemberCollectionS(Set<MemberCollectionVO> memberCollectionS) {
		this.memberCollectionS = memberCollectionS;
	}


	public Set<ArticleSubReportVO> getArticleSubReportS() {
		return articleSubReportS;
	}


	public void setArticleSubReportS(Set<ArticleSubReportVO> articleSubReportS) {
		this.articleSubReportS = articleSubReportS;
	}


	public Set<ArticleCollectionVO> getArticleCollectionS() {
		return articleCollectionS;
	}


	public void setArticleCollectionS(Set<ArticleCollectionVO> articleCollectionS) {
		this.articleCollectionS = articleCollectionS;
	}


	public Set<ArticleVO> getArticleS() {
		return articleS;
	}


	public void setArticleS(Set<ArticleVO> articleS) {
		this.articleS = articleS;
	}


	public Set<ArticleSubVO> getArticleSubS() {
		return articleSubS;
	}


	public void setArticleSubS(Set<ArticleSubVO> articleSubS) {
		this.articleSubS = articleSubS;
	}


	public Set<ArticleReportVO> getArticleReportS() {
		return articleReportS;
	}


	public void setArticleReportS(Set<ArticleReportVO> articleReportS) {
		this.articleReportS = articleReportS;
	}
	
	public Set<ArticleReactionVO> getArticleReactionS() {
		return articleReactionS;
	}


	public void setArticleReactionS(Set<ArticleReactionVO> articleReactionS) {
		this.articleReactionS = articleReactionS;
	}

//	@Override
//	public String toString() {
//		return "MembersVO [memberId=" + memberId + ", recipeReactionS=" + recipeReactionS + ", recipeCommentsReportS="
//				+ recipeCommentsReportS + ", recipeReportS=" + recipeReportS + ", recipeCollectionS="
//				+ recipeCollectionS + ", recipeS=" + recipeS + ", shoppingCartS=" + shoppingCartS + ", memberOrderS="
//				+ memberOrderS + ", promoCodeUsedS=" + promoCodeUsedS + ", notifyCenterS=" + notifyCenterS
//				+ ", memberCollectionS=" + memberCollectionS + ", articleSubReportS=" + articleSubReportS
//				+ ", articleCollectionS=" + articleCollectionS + ", articleS=" + articleS + ", articleSubS="
//				+ articleSubS + ", articleReportS=" + articleReportS + ", memberAccount=" + memberAccount
//				+ ", memberPassword=" + memberPassword + ", memberIntroduce=" + memberIntroduce + ", memberCellphone="
//				+ memberCellphone + ", memberMail=" + memberMail + ", memberDate=" + memberDate + ", memberAddress="
//				+ memberAddress + ", memberCountry=" + memberCountry + ", memberStatus=" + memberStatus
//				+ ", memberPicture=" + Arrays.toString(memberPicture) + ", memberNickname=" + memberNickname
//				+ ", memberGender=" + memberGender + ", credcreatedTimestamp=" + credcreatedTimestamp
//				+ ", lastEditTimestamp=" + lastEditTimestamp + "]";
//	}
	@Override
	public String toString() {
		return "MembersVO [memberId=" + memberId + ", memberAccount=" + memberAccount + ", memberPassword="
				+ memberPassword + ", memberIntroduce=" + memberIntroduce + ", memberCellphone=" + memberCellphone
				+ ", memberMail=" + memberMail + ", memberDate=" + memberDate + ", memberAddress=" + memberAddress
				+ ", memberCountry=" + memberCountry + ", memberStatus=" + memberStatus + ", memberPicture="
				+ Arrays.toString(memberPicture) + ", memberNickname=" + memberNickname + ", memberGender="
				+ memberGender + ", credcreatedTimestamp=" + credcreatedTimestamp + ", lastEditTimestamp="
				+ lastEditTimestamp + "]";
	}


	
	
	
	
	
	

	
}
