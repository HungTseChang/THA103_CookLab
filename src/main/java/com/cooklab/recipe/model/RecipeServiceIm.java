package com.cooklab.recipe.model;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.cooklab.members.model.MembersVO;
import com.cooklab.product.model.ProductHDAOrRecipeTest;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.CreateRecipeDTO;
import com.cooklab.recipe.CreateRecipeDTO.IngredientDTO;
import com.cooklab.recipe.CreateRecipeDTO.StepDTO;
import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareVO;
import com.cooklab.recipe_step.model.RecipeStepVO;
import com.cooklab.util.HibernateUtil;

public class RecipeServiceIm implements RecipeService {
	private RecipeDAO dao;

	public RecipeServiceIm() {
		dao = new RecipeHDAOIm(HibernateUtil.getSessionFactory());
	}

	@Override
	public RecipeVO addRecipe(MembersVO members, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Byte recipeQuantity) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setMembers(members);
		recipeVO.setRecipeName(recipeName);
		recipeVO.setCoverImage(coverImage);
		recipeVO.setIntroduction(introduction);
		recipeVO.setAdditionalExplanation(additionalExplanation);
		recipeVO.setRegion(region);
		recipeVO.setRecipeStatus(recipeStatus);
		recipeVO.setRecipeQuantity(recipeQuantity);
		dao.insert(recipeVO);

		return recipeVO;
	}

	@Override
	public RecipeVO updateRecipe(Integer recipeNo, MembersVO members, String recipeName, byte[] coverImage,
			String introduction, String additionalExplanation, String region, Byte recipeStatus, Integer reportCount,
			Integer viewCount, Byte recipeQuantity, Timestamp lastEditTimestamp) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipeNo(recipeNo);
		recipeVO.setMembers(members);
		recipeVO.setRecipeName(recipeName);
		if (coverImage != null) {
			recipeVO.setCoverImage(coverImage);
		} else {
			coverImage = dao.findByPrimaryKey(recipeNo).getCoverImage();
			recipeVO.setCoverImage(coverImage);
		}
		recipeVO.setIntroduction(introduction);
		recipeVO.setAdditionalExplanation(additionalExplanation);
		recipeVO.setRegion(region);
		recipeVO.setRecipeStatus(recipeStatus);
		recipeVO.setReportCount(reportCount);
		recipeVO.setViewCount(viewCount);
		recipeVO.setRecipeQuantity(recipeQuantity);
		recipeVO.setLastEditTimestamp(lastEditTimestamp);
		dao.update(recipeVO);

		return recipeVO;
	}

	@Override
	public void deleteRecipe(Integer recipeNo) {
		dao.delete(recipeNo);
	}

	@Override
	public RecipeVO getOneRecipe(Integer recipeNo) {
		return dao.findByPrimaryKey(recipeNo);
	}

	@Override
	public List<RecipeVO> getAll() {
		return dao.getAll();
	}

	@Override
	public Integer createRecipe(MembersVO memberVO, CreateRecipeDTO createRecipeDTO) {

		RecipeVO recipeVO = new RecipeVO();
		ProductHDAOrRecipeTest productHDAO = new ProductHDAOrRecipeTest(HibernateUtil.getSessionFactory());

		recipeVO.setRecipeName(createRecipeDTO.getRecipeName());
		recipeVO.setMembers(memberVO);
		recipeVO.setCoverImage(Base64.getDecoder().decode(createRecipeDTO.getCoverImage()));
		recipeVO.setIntroduction(createRecipeDTO.getIntroduction());
		recipeVO.setAdditionalExplanation(createRecipeDTO.getAdditionalExplanation());
		recipeVO.setRegion(createRecipeDTO.getRegion());
		recipeVO.setRecipeStatus((byte) 1);
		recipeVO.setRecipeQuantity(createRecipeDTO.getRecipeQuantity());

		Set<RecipeIngredientVO> ingredientSet = new HashSet<>();
		for (IngredientDTO ingredientDTO : createRecipeDTO.getIngredient()) {
			RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();
			recipeIngredientVO.setRecipe(recipeVO);
			ProductVO productVO = productHDAO.findByProductName(ingredientDTO.getIngredient(), "ingredientCategoryNo");
			if(productVO!=null) {
				recipeIngredientVO.setProduct(productVO);
			}else {
				recipeIngredientVO.setTextLabel(ingredientDTO.getIngredient());
			}
			recipeIngredientVO.setIngredientQuantity(ingredientDTO.getIngredientQuantity());
			ingredientSet.add(recipeIngredientVO);
		}
		recipeVO.setIngredient(ingredientSet);
		
		Set<RecipeKitchenwareVO> kitchenwareSet = new HashSet<>();
		for (String recipeKitchenware : createRecipeDTO.getKitchenware()) {
			RecipeKitchenwareVO recipeKitchenwareVO = new RecipeKitchenwareVO();
			recipeKitchenwareVO.setRecipe(recipeVO);
			ProductVO productVO = productHDAO.findByProductName(recipeKitchenware, "kitchenwareCategoryNo");
			if(productVO!=null) {
				recipeKitchenwareVO.setProduct(productVO);
			}else {
				recipeKitchenwareVO.setTextLabel(recipeKitchenware);
			}
			kitchenwareSet.add(recipeKitchenwareVO);
		}
		recipeVO.setKitchenware(kitchenwareSet);
		
		Set<RecipeStepVO> stepSet = new HashSet<>();
		Integer stepCount =0;
		for(StepDTO stepDTO:createRecipeDTO.getStep()) {
			RecipeStepVO recipeStepVO= new RecipeStepVO();
			recipeStepVO.setRecipe(recipeVO);
			recipeStepVO.setStep(++stepCount);
			recipeStepVO.setStepImg(stepDTO.getStepImg()!=null?Base64.getDecoder().decode(stepDTO.getStepImg()):null);
			recipeStepVO.setStepTime(stepDTO.getStepTime());
			recipeStepVO.setStepContent(stepDTO.getStepContent());
			stepSet.add(recipeStepVO);
		}
		recipeVO.setStep(stepSet);

		return dao.insert(recipeVO);
	}

	@Override
	public List<RecipeVO> getByPage(String cloumn, boolean desc, Integer offset, Integer limit) {
		return dao.getByPage(cloumn, desc, offset, limit);
	}

	@Override
	public long getCount() {
		return dao.getCount();
	}
}
