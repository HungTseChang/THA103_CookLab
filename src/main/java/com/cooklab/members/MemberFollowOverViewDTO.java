package com.cooklab.members;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.member_collection.model.MemberCollectionVO;
import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.*;
import com.cooklab.recipe.RecipeBreowseDTO.StepDTO;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.recipe_step.model.RecipeStepVO;

public class MemberFollowOverViewDTO {
	//關注食譜
	List<RecipeCollectionDTO> RecipeList = new ArrayList<>();
	//關注會員
	List<MembersCollectionDTO> MemberList = new ArrayList<>();
	//關注文章
	List<ArticleCollectionDTO> ArticleList = new ArrayList<>();
	public MemberFollowOverViewDTO(MembersVO membersVO)
	
	
	{
		for(RecipeCollectionVO rcVO : membersVO.getRecipeCollectionS())
		{
			this.RecipeList.add(new RecipeCollectionDTO(rcVO));
		}
		for(MemberCollectionVO mcVO : membersVO.getMemberCollectionS())
		{
			this.MemberList.add(new MembersCollectionDTO(mcVO));
		}

		for(ArticleCollectionVO acVO : membersVO.getArticleCollectionS())
		{
			this.ArticleList.add(new ArticleCollectionDTO(acVO));
		}
	}
	
	public class MembersCollectionDTO{
		private Integer memberId;
		private String memberNickName;
		private String memberPicture;
		private byte memberStatus;
		private String memberIntorduce;
		//近期食譜
		List<RecipeSDTO> RecipeList = new ArrayList<>();


		public MembersCollectionDTO(MemberCollectionVO memberCollectionVO) {
			this.memberId = memberCollectionVO.getMemberIdCollectioned().getMemberId();
			this.memberNickName = memberCollectionVO.getMemberIdCollectioned().getMemberNickname();
			if(memberCollectionVO.getMemberIdCollectioned().getMemberPicture() != null)
				this.memberPicture = Base64.getEncoder().encodeToString(memberCollectionVO.getMemberIdCollectioned().getMemberPicture());
			this.memberStatus = memberCollectionVO.getMemberIdCollectioned().getMemberStatus();
			this.memberIntorduce = memberCollectionVO.getMemberIdCollectioned().getMemberIntroduce();
			
			for(RecipeVO rVO : memberCollectionVO.getMemberIdCollectioned().getRecipeS())
			{
				this.RecipeList.add(new RecipeSDTO(rVO));
			}
		}
		//關注的會員的食譜 =*****
		public class RecipeSDTO{
			private String recipeName;
			private Integer recipeNo;
		    private String coverImage;		//封面圖片
		    private String introduction;	//簡介
		    private byte recipeStatus;		//食譜狀態
		    private Integer toatalStepTime = 0;
			public RecipeSDTO() {

			}
			public RecipeSDTO(RecipeVO recipeVO) {
				this.recipeName = recipeVO.getRecipeName();
				this.recipeNo = recipeVO.getRecipeNo();
				if(recipeVO.getCoverImage() != null)
					this.coverImage = Base64.getEncoder().encodeToString(recipeVO.getCoverImage());
				this.introduction = recipeVO.getIntroduction();
				this.recipeStatus = recipeVO.getRecipeStatus();
				for(RecipeStepVO recipeStepVO: recipeVO.getStep()) {
					toatalStepTime = recipeStepVO.getStepTime() + toatalStepTime;
				}
			}
			
			public class StepDTO{
				private Integer stepTime;
				public StepDTO() {

				}
				public StepDTO(RecipeStepVO recipeStepVO) {
					this.stepTime = recipeStepVO.getStepTime();
				}
			}
		}
	}

	public class ArticleCollectionDTO{
		private Integer articleNo;
		private String articleCategory;
		private String articleTitle;
		private byte articleStatus;
		private String memberNickname;
		private Integer articleCollectionNo;
		
		public ArticleCollectionDTO(ArticleCollectionVO articleCollectionVO){
			this.articleCollectionNo = articleCollectionVO.getArticleCollectionNo();
			this.articleNo=articleCollectionVO.getArticle().getArticleNo();
			this.articleCategory=articleCollectionVO.getArticle().getArticleCategory().getArticleCategory();
			this.articleTitle=articleCollectionVO.getArticle().getArticleTitle();
			this.articleStatus=articleCollectionVO.getArticle().getArticleStatus();
			this.memberNickname = articleCollectionVO.getArticle().getMembers().getMemberNickname();
		}
	}
	

	
	public class RecipeCollectionDTO {
		private Integer recipeNo;
	    private String coverImage;		//封面圖片
	    private String recipeName;		//食譜名稱
	    private String introduction;	//簡介
	    private byte recipeStatus;		//食譜狀態
	    private Integer recipeCollectionNo; //食譜收藏編號
	    private Integer toatalStepTime = 0;
		List<StepDTO> step = new ArrayList<>();
		
		public RecipeCollectionDTO(RecipeCollectionVO RecipeCollectionVO) {
			this.recipeCollectionNo = RecipeCollectionVO.getCollectionNo();
			this.recipeNo = RecipeCollectionVO.getRecipe().getRecipeNo();		
			if(RecipeCollectionVO.getRecipe().getCoverImage() != null)
				this.coverImage = Base64.getEncoder().encodeToString(RecipeCollectionVO.getRecipe().getCoverImage());
			this.recipeName = RecipeCollectionVO.getRecipe().getRecipeName();
			this.introduction = RecipeCollectionVO.getRecipe().getIntroduction();
			this.recipeStatus = RecipeCollectionVO.getRecipe().getRecipeStatus();
			
			for(RecipeStepVO recipeStepVO: RecipeCollectionVO.getRecipe().getStep()) {
				toatalStepTime = recipeStepVO.getStepTime() + toatalStepTime;
			}
		}
		
		public class StepDTO{
			private Integer stepTime;
			public StepDTO() {

			}
			public StepDTO(RecipeStepVO recipeStepVO) {
				this.stepTime = recipeStepVO.getStepTime();
			}
		}
	}
	
}
