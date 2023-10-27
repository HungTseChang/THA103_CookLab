package com.cooklab.members.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberRecipeOverViewDTO;
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
			List<RecipeVO> list = getSession().createQuery("from RecipeVO where members.memberId=:memberId AND recipeStatus=1", RecipeVO.class)
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
}
