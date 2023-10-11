package com.cooklab.recipe.model;

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

import com.cooklab.recipe_step.model.RecipeStepVO;

@Entity
@Table(name = "recipe")
public class RecipeVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_no",insertable = false, updatable = false)
	private Integer recipeNo;

//	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
//	@OrderBy("recipe_no asc")
//	private Set<RecipeReactionVO> reaction;
//
//	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
//	@OrderBy("recipe_no asc")
//	private Set<RecipeCommentsVO> comments;
//
//	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
//	@OrderBy("recipe_no asc")
//	private Set<RecipeReportVO> report;
//
//	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
//	@OrderBy("recipe_no asc")
//	private Set<RecipeCollectionVO> collection;
//
//	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
//	@OrderBy("recipe_no asc")
//	private Set<RecipeHashtagVO> hashtag;
//
//	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
//	@OrderBy("recipe_no asc")
//	private Set<RecipeIngredientVO> ingredient;
//
//	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
//	@OrderBy("recipe_no asc")
//	private Set<RecipeKitchenwareVO> kitchenware;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeStepVO> step;

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
	private Timestamp lastEditTimestamp;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	public RecipeVO() {
	}

	public RecipeVO(Integer recipeNo, Integer memberId, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Integer reportCount, Integer viewCount,
			Byte recipeQuantity, Timestamp lastEditTimestamp, Timestamp createdTimestamp) {
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

//	public Set<RecipeReactionVO> getReaction() {
//		return reaction;
//	}
//
//	public void setReaction(Set<RecipeReactionVO> reaction) {
//		this.reaction = reaction;
//	}
//
//	public Set<RecipeCommentsVO> getComments() {
//		return comments;
//	}
//
//	public void setComments(Set<RecipeCommentsVO> comments) {
//		this.comments = comments;
//	}

//	public Set<RecipeReportVO> getReport() {
//		return report;
//	}
//
//	public void setReport(Set<RecipeReportVO> report) {
//		this.report = report;
//	}
//
//	public Set<RecipeCollectionVO> getCollection() {
//		return collection;
//	}
//
//	public void setCollection(Set<RecipeCollectionVO> collection) {
//		this.collection = collection;
//	}
//
//	public Set<RecipeHashtagVO> getHashtag() {
//		return hashtag;
//	}
//
//	public void setHashtag(Set<RecipeHashtagVO> hashtag) {
//		this.hashtag = hashtag;
//	}
//
//	public Set<RecipeIngredientVO> getIngredient() {
//		return ingredient;
//	}
//
//	public void setIngredient(Set<RecipeIngredientVO> ingredient) {
//		this.ingredient = ingredient;
//	}
//
//	public Set<RecipeKitchenwareVO> getKitchenware() {
//		return kitchenware;
//	}
//
//	public void setKitchenware(Set<RecipeKitchenwareVO> kitchenware) {
//		this.kitchenware = kitchenware;
//	}

	public Set<RecipeStepVO> getStep() {
		return step;
	}

	public void setStep(Set<RecipeStepVO> step) {
		this.step = step;
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

	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}

	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
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
