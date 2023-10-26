package com.cooklab.members.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.purchase_order.model.PurchaseOrderVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(membersVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return 0;
	}
	@Override
	public boolean update(MembersVO membersVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

	        session.beginTransaction();

	        //獲取現在的 MembersVO
	        MembersVO existingMember = (MembersVO) session.get(MembersVO.class, membersVO.getMemberId());
	        membersVO.setMemberPassword(existingMember.getMemberPassword());
//	        existingMember.setMemberId(membersVO.getMemberId());
//	        existingMember.setMemberStatus(membersVO.getMemberStatus());
	        
			session.merge(membersVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return true;
	}
	@Override
	public boolean delete(Integer memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MembersVO vo = session.get(MembersVO.class, memberId);
			if(vo !=null) {
				session.delete(vo);
			}
			session.getTransaction().commit();
			System.out.println("搜尋");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return true;
	}
	@Override
	public MembersVO findByPrimaryKey(Integer memberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MembersVO memVO = session.createQuery("from MembersVO where member_id =" + memberId, MembersVO.class)
					.uniqueResult();

			session.getTransaction().commit();
			return memVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}
	@Override
	public MembersVO findByMembersAccout(String memberAccount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			MembersVO memVO = new MembersVO();
			session.beginTransaction();
			MembersVO memVO = session.createQuery("from MembersVO where memberAccount ='" + memberAccount+"'", MembersVO.class)
					.uniqueResult();

			session.getTransaction().commit();
			return memVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}
	@Override
	public boolean updateMemberStatus(MembersVO membersVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
	        session.beginTransaction();

	        //獲取現在的 MembersVO
	        MembersVO existingMember = (MembersVO) session.get(MembersVO.class, membersVO.getMemberId());

	        existingMember.setMemberId(membersVO.getMemberId());
	        existingMember.setMemberStatus(membersVO.getMemberStatus());
	        
			session.merge(existingMember);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return true;
	}
	@Override
	public boolean updateMemberPassword(MembersVO membersVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
	        session.beginTransaction();

	        //獲取現在的 MembersVO
	        MembersVO existingMember = (MembersVO) session.get(MembersVO.class, membersVO.getMemberId());

	        existingMember.setMemberId(membersVO.getMemberId());
	        existingMember.setMemberPassword(membersVO.getMemberPassword());
	        
			session.merge(existingMember);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return true;
	}
	@Override
	public List<MembersVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<MembersVO> list = session.createQuery("from MembersVO ", MembersVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}


}
