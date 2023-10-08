package com.cooklab.members.model;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;


public class MembersTest {
	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		SimpleDateFormat  dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
		Date  date = dateFormat.parse("1998-09-08");
		
		try {			
			//新增資料  member_account, member_password, member_introduce, member_cellphone, 
			//member_mail, member_date, member_address, member_country, member_status, 
			//        member_picture, member_nickname, member_gender
			session.beginTransaction();
			
			MembersVO m1 =new MembersVO();
//			m1.setMemberAccount("QQ12345");
//			m1.setMemberPassword("ytrewq");
//			m1.setMemberIntroduce("大家好我是 QQ ORZ");
//			m1.setMemberCellphone("0911587487");
//			m1.setMemberMail("1234567@gmail.com.");
//			m1.setMemberDate(date);
//			m1.setMemberAddress("苗栗縣苗栗市");
//			m1.setMemberCountry("台灣");
//			m1.setMemberStatus((byte)0);
//			m1.setMemberPicture((byte[])null);
//			m1.setMemberNickname("苗栗天龍仁");
//			m1.setMemberGender((byte)1);
//			
//			session.save(m1);
			
			//更新資料
//			m1 = session.get(MembersVO.class, 1);
//			m1.setMemberCountry("阿拉刮花");
			
			//刪除資料
//			m1.setMemberId(10);
//			session.delete(m1);	
	
			//查詢全部
			Query<MembersVO> query3 = session.createQuery(
					" from MembersVO", MembersVO.class);
			List<MembersVO> list2 = query3.list();
			System.out.println(list2);
			
			
			//查詢資料(單筆)
//			MembersVO membersVO = session.createQuery("from MembersVO where memberId = 3", MembersVO.class).uniqueResult();
//			System.out.println(membersVO);
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
