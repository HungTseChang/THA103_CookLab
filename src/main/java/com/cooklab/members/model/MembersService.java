package com.cooklab.members.model;

import java.util.List;

import javax.persistence.EntityManager;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;

public class MembersService {

	

	private MembersDAO_interface dao;

	public MembersService() {
		dao = new MembersHDAOIm(HibernateUtil.getSessionFactory());
	}

	public MembersVO addMembers(String memberAccount, String memberPassword,String memberIntroduce,
			String memberCellphone,String memberMail, java.sql.Date memberDate,String memberAddress,String memberCountry
			,Byte memberStatus, String memberNickname,Byte memberGender
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
//		membersVO.setMemberPicture(memberPicture);
		
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
}
