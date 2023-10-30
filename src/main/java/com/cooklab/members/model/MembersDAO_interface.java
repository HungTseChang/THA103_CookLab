package com.cooklab.members.model;
import java.util.List;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;



public interface MembersDAO_interface {
	public int insert(MembersVO membersVO);
    public boolean update(MembersVO membersVO);
    public boolean delete(Integer memberId);
    public MembersVO findByPrimaryKey(Integer memberId);
    public MembersVO findByMembersAccout(String memberAccount);
    public boolean updateMemberStatus(MembersVO membersVO);
    public List<MembersVO> getAll();
	boolean updateMemberPassword(MembersVO membersVO);
	public List<RecipeVO> MemberRecipeRead(Integer memberId);
	//
	public List<RecipeVO> getByPage(Integer offset, Integer limit,Integer memberId);
	public List<MemberOrderVO> getOrder(Integer offset, Integer limit,Integer memberId);
	public List<MembersVO> getFollow(Integer offset, Integer limit,Integer memberId);
	public List<NotifyCenterVO> getNotify(Integer offset, Integer limit,Integer memberId);
	public List<ArticleVO> getArticle(Integer offset, Integer limit,Integer memberId);
	//
	public void addMembersColloection(MembersVO Collectioned,MembersVO memberId);
	public void deleteMemberColloection(MembersVO Collectioned,MembersVO memberId,Integer MemberCollectionNo);
	public Integer findMemberCollectionPK(MembersVO Collectioned,MembersVO memberId);
	//

	public ArticleCollectionVO findByMemberAndArticle(ArticleVO articleVO, MembersVO membersVO);
	public boolean DeleteArticleCollection(Integer articCollectionNo);
}


