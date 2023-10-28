package com.cooklab.recipe_comments.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_comments_report.model.RecipeCommentsReportVO;

@Entity
@Table(name = "recipe_comments")
public class RecipeCommentsVO implements java.io.Serializable{	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_comments_no", insertable = false ,updatable = false)
	private Integer recipeCommentsNo;	//食譜留言編號(PK)
	@OneToMany(mappedBy = "recipeComments", cascade = CascadeType.ALL)
	@OrderBy("recipe_no asc")
	private Set<RecipeCommentsReportVO> report;	//留言檢舉關聯
	@ManyToOne
	@JoinColumn(name = "recipe_no", referencedColumnName = "recipe_no")
	private RecipeVO recipe;			//食譜編號(FK)
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private MembersVO members;			//會員編號(FK)
	@Column(name = "comment_content")
	private String commentContent;		//留言內文
	@Column(name = "report_comments" , insertable = false)
	private Integer reportComments;		//檢舉次數
	@Column(name = "last_edit_timestamp", insertable = false)
	private Timestamp lastEditTimestamp;	//最後編輯時間
	@Column(name = "created_timestamp", insertable = false ,updatable = false)
	private Timestamp createdTimestamp;		//留言時間
	
	public RecipeCommentsVO() {
	}

	
	
	public Integer getRecipeCommentsNo() {
		return recipeCommentsNo;
	}



	public void setRecipeCommentsNo(Integer recipeCommentsNo) {
		this.recipeCommentsNo = recipeCommentsNo;
	}



	public Set<RecipeCommentsReportVO> getReport() {
		return report;
	}



	public void setReport(Set<RecipeCommentsReportVO> report) {
		this.report = report;
	}



	public RecipeVO getRecipe() {
		return recipe;
	}



	public void setRecipe(RecipeVO recipe) {
		this.recipe = recipe;
	}



	public MembersVO getMembers() {
		return members;
	}



	public void setMembers(MembersVO members) {
		this.members = members;
	}



	public String getCommentContent() {
		return commentContent;
	}



	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}



	public Integer getReportComments() {
		return reportComments;
	}



	public void setReportComments(Integer reportComments) {
		this.reportComments = reportComments;
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
		return "RecipeCommentsVO [recipeCommentsNo=" + recipeCommentsNo + ", recipe=" + recipe + ", members="
				+ members + ", commentContent=" + commentContent + ", reportComments=" + reportComments
				+ ", lastEditTimestamp=" + lastEditTimestamp + ", createdTimestamp=" + createdTimestamp + "]";
	}

	
	
}
