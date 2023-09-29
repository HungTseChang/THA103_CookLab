package com.cooklab.recipe.model;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class RecipeVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_no", updatable = false)
	private Integer recipeNo;
	@Column(name = "member_id")
	private Integer memberId;
	@Column(name = "recipe_name")
	private String recipeName;
	@Column(name = "cover_image", columnDefinition = "LONGBLOB")
	private byte[] coverImage;
	@Column(name = "introduction")
	private String introduction;
	@Column(name = "additional_explanation")
	private String additionalExplanation;
	@Column(name = "region")
	private String region;
	@Column(name = "recipe_status")
	private Byte recipeStatus;
	@Column(name = "report_count")
	private Integer reportCount;
	@Column(name = "view_count")
	private Integer viewCount;
	@Column(name = "recipe_quantity")
	private Byte recipeQuantity;
	@Column(name = "last_edit_timestamp")
	private Date lastEditTimestamp;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Date createdTimestamp;

	public RecipeVO() {
	}
	

	public RecipeVO(Integer recipeNo, Integer memberId, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Integer reportCount, Integer viewCount,
			Byte recipeQuantity, Date lastEditTimestamp, Date createdTimestamp) {
		super();
		this.recipeNo = recipeNo;
		this.memberId = memberId;
		this.recipeName = recipeName;
		this.coverImage = coverImage;
		this.introduction = introduction;
		this.additionalExplanation = additionalExplanation;
		this.region = region;
		this.recipeStatus = recipeStatus;
		this.reportCount = reportCount;
		this.viewCount = viewCount;
		this.recipeQuantity = recipeQuantity;
		this.lastEditTimestamp = lastEditTimestamp;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public byte[] getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(byte[] coverImage) {
		this.coverImage = coverImage;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAdditionalExplanation() {
		return additionalExplanation;
	}

	public void setAdditionalExplanation(String additionalExplanation) {
		this.additionalExplanation = additionalExplanation;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Byte getRecipeStatus() {
		return recipeStatus;
	}

	public void setRecipeStatus(Byte recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Byte getRecipeQuantity() {
		return recipeQuantity;
	}

	public void setRecipeQuantity(Byte recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}

	public Date getLastEditTimestamp() {
		return lastEditTimestamp;
	}

	public void setLastEditTimestamp(Date lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeVO [recipeNo=" + recipeNo + ", memberId=" + memberId + ", recipeName=" + recipeName
				+ ", coverImage=" + Arrays.toString(coverImage) + ", introduction=" + introduction
				+ ", additionalExplanation=" + additionalExplanation + ", region=" + region + ", recipeStatus="
				+ recipeStatus + ", reportCount=" + reportCount + ", viewCount=" + viewCount + ", recipeQuantity="
				+ recipeQuantity + ", lastEditTimestamp=" + lastEditTimestamp + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}
}
