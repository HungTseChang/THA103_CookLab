package com.cooklab.members.model;

import com.cooklab.member_collection.model.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.purchase_order.model.PurchaseOrderVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.util.HibernateUtil;
import java.util.Set; // 在你的Java文件开头添加这个导入语句

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
public class MembersHDAOIm implements MembersDAO_interface{

	private SessionFactory factory;
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	public MembersHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	

	@Override
	public int insert(MembersVO membersVO) {
		
		return (Integer) getSession().save(membersVO);
	}
	@Override
	public boolean update(MembersVO membersVO) {

	        MembersVO existingMember = (MembersVO) getSession().get(MembersVO.class, membersVO.getMemberId());
	        membersVO.setMemberPassword(existingMember.getMemberPassword());
	        try {
	        	getSession().merge(membersVO);
	        	return true;
	        }catch(Exception e)
	        {
	    		return false;
	        }
	}
	@Override
	public boolean delete(Integer memberId) {
			MembersVO vo = getSession().get(MembersVO.class, memberId);
			if(vo !=null) {
				getSession().delete(vo);
				return true;
			}
			else
				return false;

		
	}
	@Override
	public MembersVO findByPrimaryKey(Integer memberId) {

		return getSession().createQuery("from MembersVO where member_id =" + memberId, MembersVO.class).uniqueResult();
	}
	@Override
	public MembersVO findByMembersAccout(String memberAccount) {

			return getSession().createQuery("from MembersVO where memberAccount ='" + memberAccount+"'", MembersVO.class)
					.uniqueResult();
	}
	@Override
	public boolean updateMemberStatus(MembersVO membersVO) {
	        //獲取現在的 MembersVO
	        MembersVO existingMember = (MembersVO) getSession().get(MembersVO.class, membersVO.getMemberId());

	        existingMember.setMemberId(membersVO.getMemberId());
	        existingMember.setMemberStatus(membersVO.getMemberStatus());
	        
			getSession().merge(existingMember);

		return true;
	}
	@Override
	public boolean updateMemberPassword(MembersVO membersVO) {
	        //獲取現在的 MembersVO
	        MembersVO existingMember = (MembersVO) getSession().get(MembersVO.class, membersVO.getMemberId());

	        existingMember.setMemberId(membersVO.getMemberId());
	        existingMember.setMemberPassword(membersVO.getMemberPassword());
	        
			getSession().merge(existingMember);
		return true;
	}
	@Override
	public List<MembersVO> getAll() {
			
			return getSession().createQuery("from MembersVO ", MembersVO.class).list();
	}

	@Override
	public List<RecipeVO> MemberRecipeRead(Integer memberId) {
			List<RecipeVO> list1 = getSession().createQuery("from RecipeVO where members.memberId=:memberIdE", RecipeVO.class).setParameter("memberIdE",memberId).list();	//members
			 // 強迫不要LAZY
		    for (RecipeVO recipe : list1) {
		        recipe.getStep().size(); 
		    }
			return list1;
    }

	@Override
	public List<RecipeVO> getByPage(Integer offset, Integer limit,Integer memberId) {
			List<RecipeVO> list = getSession().createQuery("from RecipeVO where members.memberId=:memberId", RecipeVO.class)
					.setParameter("memberId",memberId).setFirstResult(offset)
					.setMaxResults(limit).list();
			
			 // 強迫不要LAZY
		    for (RecipeVO recipe : list) {
		        recipe.getStep().size(); 
		    }
			for (RecipeVO recipe1 : list) {
			      recipe1.getHashtag().size();
			}
	    
			return list;

	}
	@Override
	public List<MemberOrderVO> getOrder(Integer offset, Integer limit, Integer memberId) {
		List<MemberOrderVO> list = getSession().createQuery("from MemberOrderVO where members.memberId=:memberId", MemberOrderVO.class)
				.setParameter("memberId",memberId).setFirstResult(offset)
				.setMaxResults(limit).list();
		return list;
	}
	@Override
	public List<MembersVO> getFollow(Integer offset, Integer limit, Integer memberId) {
		List<MembersVO> list = getSession().createQuery("FROM MembersVO m " +
			    "LEFT JOIN FETCH m.recipeCollectionS rc " +
			    "LEFT JOIN FETCH m.memberCollectionS mc " +
			    "LEFT JOIN FETCH m.articleCollectionS ac " +
			    "WHERE m.memberId = :memberId", MembersVO.class)
				.setParameter("memberId",memberId).setFirstResult(offset)
				.setMaxResults(limit).list();
		return list;
	}
	@Override
	public List<NotifyCenterVO> getNotify(Integer offset, Integer limit, Integer memberId) {
		List<NotifyCenterVO> list = getSession().createQuery("from NotifyCenterVO where members.memberId=:memberId", NotifyCenterVO.class)
				.setParameter("memberId",memberId).setFirstResult(offset)
				.setMaxResults(limit).list();
		return list;
	}
	@Override
	public List<ArticleVO> getArticle(Integer offset, Integer limit,Integer memberId){
		List<ArticleVO> list = getSession().createQuery("from ArticleVO where members.memberId=:memberId", ArticleVO.class)
				.setParameter("memberId",memberId).setFirstResult(offset)
				.setMaxResults(limit).list();
		return list;
	}
	@Override
	public void addMembersColloection(MembersVO Collectioned,MembersVO memberId) {
		MemberCollectionVO mcVO = new MemberCollectionVO();
		mcVO.setMemberIdCollectioned(Collectioned);
		mcVO.setMembers(memberId);
		getSession().save(mcVO);
		
		
		
//		getSession()
//		getSession().save(membersVO);
//		System.out.println(getSession().createQuery("from MemberCollectionVO where memberIdCollectioned=:memberIdCollectioned AND memberId=:memberId ", MemberCollectionVO.class)
//				.setParameter("memberIdCollectioned",memberIdCollectioned).setParameter("memberId",memberId).toString());
//		getSession().save(memberId);
	}
	@Override
	public void deleteMemberColloection(MembersVO Collectioned,MembersVO memberId,Integer MemberCollectionNo) {
		MemberCollectionVO mcVO = new MemberCollectionVO();
		mcVO.setMemberIdCollectioned(Collectioned);
		mcVO.setMembers(memberId);
		mcVO.setMemberCollectionNo(MemberCollectionNo);
		getSession().clear();
		getSession().delete(mcVO);
		
	}

	//	修改會員關注狀態 ==================================================================================
	public Integer findMemberCollectionPK(MembersVO Collectioned,MembersVO memberId)
	{
		MemberCollectionVO mcVO = new MemberCollectionVO();
		mcVO = getSession().createQuery("from MemberCollectionVO where memberIdCollectioned=:Collectioned AND members=:memberId", MemberCollectionVO.class)
		.setParameter("Collectioned",Collectioned).setParameter("memberId",memberId).uniqueResult();
		return mcVO.getMemberCollectionNo();
	}

	@Override
	public ArticleCollectionVO findByMemberAndArticle(ArticleVO articleVO, MembersVO membersVO) {
		return getSession().createQuery("from ArticleCollectionVO where members = :members and article = :article",
				ArticleCollectionVO.class).setParameter("members", membersVO).setParameter("article", articleVO).uniqueResult();
	}
	@Override
	public boolean DeleteArticleCollection(Integer articCollectionNo) {

		ArticleCollectionVO artVO = getSession().get(ArticleCollectionVO.class, articCollectionNo);
		if(artVO !=null) {
			getSession().delete(artVO);
			return true;
		}
		else
			return false;
		
	}
	

	//	修改食譜關注狀態 ==================================================================================
}
