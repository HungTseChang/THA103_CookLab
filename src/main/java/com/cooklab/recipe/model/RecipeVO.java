package com.cooklab.recipe.model;

import java.sql.Timestamp;
import java.util.Arrays;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.recipe_comments.model.RecipeCommentsVO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareVO;
import com.cooklab.recipe_reaction.model.RecipeReactionVO;
import com.cooklab.recipe_report.model.RecipeReportVO;
import com.cooklab.recipe_step.model.RecipeStepVO;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "recipe")
public class RecipeVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_no", insertable = false, updatable = false)
	private Integer recipeNo; // 食譜編號(PK)

	// 食譜反應關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeReactionVO> reaction;

	// 食譜留言關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeCommentsVO> comments;
	
	// 食譜檢舉關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeReportVO> report;

	// 食譜收藏關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeCollectionVO> collection;

	// 食譜標籤關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeHashtagVO> hashtag;

	// 食譜使用食材關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeIngredientVO> ingredient;

	// 食譜使用廚具關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeKitchenwareVO> kitchenware;

	// 食譜步驟關聯
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeStepVO> step;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", updatable = false)
	private MembersVO members; // 會員編號(FK)
	@Column(name = "recipe_name")
	private String recipeName; // 食譜名稱
	@Column(name = "cover_image", columnDefinition = "LONGBLOB")
	private byte[] coverImage; // 食譜封面
	@Column(name = "introduction")
	private String introduction; // 簡介
	@Column(name = "additional_explanation")
	private String additionalExplanation; // 補充說明
	@Column(name = "region")
	private String region; // 地區
	@Column(name = "recipe_status")
	private Byte recipeStatus; // 食譜狀態
	@Column(name = "report_count", insertable = false)
	private Integer reportCount; // 檢舉次數
	@Column(name = "view_count", insertable = false)
	private Integer viewCount; // 瀏覽人次
	@Column(name = "recipe_quantity")
	private Byte recipeQuantity; // 食譜份量
	@Column(name = "last_edit_timestamp", insertable = false)
	private Timestamp lastEditTimestamp; // 最後編輯時間
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp; // 建立時間

	public RecipeVO() {
	}

	public RecipeVO(Integer recipeNo, MembersVO members, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Integer reportCount, Integer viewCount,
			Byte recipeQuantity, Timestamp lastEditTimestamp, Timestamp createdTimestamp) {
		super();
		this.recipeNo = recipeNo;
		this.members = members;
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

	public Set<RecipeReactionVO> getReaction() {
		return reaction;
	}

	public void setReaction(Set<RecipeReactionVO> reaction) {
		this.reaction = reaction;
	}

	public Set<RecipeCommentsVO> getComments() {
		return comments;
	}

	public void setComments(Set<RecipeCommentsVO> comments) {
		this.comments = comments;
	}

	public Set<RecipeReportVO> getReport() {
		return report;
	}

	public void setReport(Set<RecipeReportVO> report) {
		this.report = report;
	}

	public Set<RecipeCollectionVO> getCollection() {
		return collection;
	}

	public void setCollection(Set<RecipeCollectionVO> collection) {
		this.collection = collection;
	}

	public Set<RecipeHashtagVO> getHashtag() {
		return hashtag;
	}

	public void setHashtag(Set<RecipeHashtagVO> hashtag) {
		this.hashtag = hashtag;
	}

	public Set<RecipeIngredientVO> getIngredient() {
		return ingredient;
	}

	public void setIngredient(Set<RecipeIngredientVO> ingredient) {
		this.ingredient = ingredient;
	}

	public Set<RecipeKitchenwareVO> getKitchenware() {
		return kitchenware;
	}

	public void setKitchenware(Set<RecipeKitchenwareVO> kitchenware) {
		this.kitchenware = kitchenware;
	}

	public Set<RecipeStepVO> getStep() {
		return step;
	}

	public void setStep(Set<RecipeStepVO> step) {
		this.step = step;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public MembersVO getMembers() {
		return members;
	}

	public void setMembers(MembersVO members) {
		this.members = members;
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
		return "RecipeVO [recipeNo=" + recipeNo + ", members=" + members + ", recipeName=" + recipeName
				+ ", coverImage=" + Arrays.toString(coverImage) + ", introduction=" + introduction
				+ ", additionalExplanation=" + additionalExplanation + ", region=" + region + ", recipeStatus="
				+ recipeStatus + ", reportCount=" + reportCount + ", viewCount=" + viewCount + ", recipeQuantity="
				+ recipeQuantity + ", lastEditTimestamp=" + lastEditTimestamp + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}
}
