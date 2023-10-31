package com.cooklab.members.model;

import java.util.List;

import javax.persistence.EntityManager;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;
import com.cooklab.member_collection.model.*;
public class MembersService {

	

	private MembersDAO_interface dao;

	public MembersService() {
		dao = new MembersHDAOIm(HibernateUtil.getSessionFactory());
	}

	public MembersVO addMembers(String memberAccount, String memberPassword,String memberIntroduce,
			String memberCellphone,String memberMail, java.sql.Date memberDate,String memberAddress,String memberCountry
			,Byte memberStatus, String memberNickname,Byte memberGender,byte[] img
			) {

		MembersVO membersVO = new MembersVO();

		membersVO.setMemberAccount(memberAccount);
		membersVO.setMemberPassword(memberPassword);
		membersVO.setMemberIntroduce(memberIntroduce);
		membersVO.setMemberCellphone(memberCellphone);
		membersVO.setMemberMail(memberMail);
		membersVO.setMemberDate(memberDate);
		membersVO.setMemberAddress(memberAddress);
		membersVO.setMemberCountry(memberCountry);
		membersVO.setMemberStatus(memberStatus);
		membersVO.setMemberNickname(memberNickname);
		membersVO.setMemberGender(memberGender);
		membersVO.setMemberPicture(img);
		
		dao.insert(membersVO);

		return membersVO;
	}

	public MembersVO updateMember(Integer memberId,String memberAccount,String memberIntroduce,
			String memberCellphone,String memberMail, java.sql.Date memberDate,String memberAddress
			, byte[] memberPicture,String memberNickname,Byte memberGender 
			) {

		MembersVO membersVO = new MembersVO();
//
		membersVO.setMemberId(memberId);
		membersVO.setMemberAccount(memberAccount);
//		membersVO.setMemberPassword(memberPassword);
		membersVO.setMemberIntroduce(memberIntroduce);
		membersVO.setMemberCellphone(memberCellphone);
		membersVO.setMemberMail(memberMail);
		membersVO.setMemberDate(memberDate);
		membersVO.setMemberAddress(memberAddress);
//		membersVO.setMemberCountry(memberCountry);
//		membersVO.setMemberStatus(memberStatus);
		membersVO.setMemberNickname(memberNickname);
		membersVO.setMemberGender(memberGender);
		membersVO.setMemberPicture(memberPicture);
		dao.update(membersVO);

		return membersVO;
	}

	public void deleteMember(Integer empno) {
		dao.delete(empno);
	}

	public MembersVO getOneMember(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}

	public MembersVO getOneMemberAccount(String memberAccount) {
		return dao.findByMembersAccout(memberAccount);
	}
	public MembersVO getOneMemberMail(String email) {
		return dao.findByMembersMail(email);
	}
	//修改使用者狀態
	public MembersVO updateMemberStatus(Integer memberId,byte memberStatus)
	{
		MembersVO membersVO = new MembersVO();
		membersVO.setMemberId(memberId);
		membersVO.setMemberStatus(memberStatus);
		dao.updateMemberStatus(membersVO);
		return membersVO;
	}
	//修改使用者密碼
	public MembersVO updateMemberPassword(Integer memberId,String memberPassword)
	{
		MembersVO membersVO = new MembersVO();
		membersVO.setMemberId(memberId);
		membersVO.setMemberPassword(memberPassword);
		dao.updateMemberPassword(membersVO);
		return membersVO;
	}
	

	public List<MembersVO> getAll() {
		return dao.getAll();
	}
	
	public List<RecipeVO> getByPage(Integer offset,Integer limit,Integer memberId) {
		return dao.getByPage(offset, limit,memberId) ;
	}
	public List<MemberOrderVO> getOrder(Integer offset, Integer limit,Integer memberId)
	{
		return dao.getOrder(offset, limit,memberId) ;
	}
	public List<MembersVO> getFollow(Integer offset, Integer limit,Integer memberId)
	{
		return dao.getFollow(offset, limit, memberId);
	}
	public MembersVO findByPrimaryKey(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}
	public List<NotifyCenterVO> getNotify(Integer offset, Integer limit, Integer memberId) {
		return dao.getNotify(offset, limit, memberId);
	}
	public List<ArticleVO> getArticle(Integer offset, Integer limit,Integer memberId){
		return dao.getArticle(offset, limit, memberId);
	}
//	修改會員關注狀態 ==================================================================================
	public void addMembersColloection(Integer memberIdCollectioned,Integer memberId)
	{
		MembersVO mCollection = new MembersVO();
		mCollection = getOneMember(memberIdCollectioned);
		
		MembersVO mMembersId = new MembersVO();
		mMembersId = getOneMember(memberId);
		
		dao.addMembersColloection(mCollection, mMembersId);
	}

	public void deleteMemberColloection(Integer memberIdCollectioned,Integer memberId) {
		MembersVO mCollection = new MembersVO();
		mCollection = getOneMember(memberIdCollectioned);
		
		MembersVO mMembersId = new MembersVO();
		mMembersId = getOneMember(memberId);
		
		Integer MemberCollectionNo = dao.findMemberCollectionPK(mCollection,mMembersId);
		
		
		dao.deleteMemberColloection(mCollection, mMembersId,MemberCollectionNo);
	}
	//查詢會員關注狀態
	public boolean SearchMemberColloection(Integer memberIdCollectioned,Integer memberId) {
		MembersVO mCollection = new MembersVO();
		mCollection = getOneMember(memberIdCollectioned);
		
		MembersVO mMembersId = new MembersVO();
		mMembersId = getOneMember(memberId);
		
		Integer MemberCollectionNo;
		if(dao.findMemberCollectionPK(mCollection,mMembersId) == null)
		{
			return true;
		}
		else
			return false;
		

	}
//	修改文章關注狀態 ==================================================================================

	public ArticleCollectionVO findByMemberAndArtcle(ArticleVO articleVO, MembersVO membersVO) {

		return dao.findByMemberAndArticle(articleVO, membersVO);
	}
	public void DeleteArticleCollection(Integer articCollectionNo) {
		dao.DeleteArticleCollection(articCollectionNo);
	}
}
