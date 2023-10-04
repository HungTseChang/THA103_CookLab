package com.cooklab.notify_center.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;


public class NotifyCenterTest {
	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		

		
		try {			
			//新增資料  member_id, notify_type, notify_read, notify_content
			session.beginTransaction();
			
			NotifyCenterVO n1 =new NotifyCenterVO();
//			n1.setMemberId(3);
//			n1.setNotifyType(4);
//			n1.setNotifyRead((byte) 0);
//			n1.setNotifyContent("大家好我不知道要幹嘛");
//			session.save(n1);
			
			//更新資料
//			NotifyCenterVO n1 = session.get(NotifyCenterVO.class, 1);
//			n1.setNotifyContent("隨便打打");
			
			//刪除資料
//			n1.setNotifyNo(3);
//			session.delete(n1);	
	
			//查詢全部
//			Query<NotifyCenterVO> query3 = session.createQuery(
//					" from NotifyCenterVO", NotifyCenterVO.class);
//			List<NotifyCenterVO> list2 = query3.list();
//			System.out.println(list2);
			
			
			//查詢資料(單筆)
			NotifyCenterVO notifyCenterVO = session.createQuery("from NotifyCenterVO where notifyNo = 3", NotifyCenterVO.class).uniqueResult();
			System.out.println(notifyCenterVO);
//			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
