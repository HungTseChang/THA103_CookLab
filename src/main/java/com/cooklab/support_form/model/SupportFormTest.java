package com.cooklab.support_form.model;

import java.util.List;

import com.cooklab.util.HibernateUtil;

public class SupportFormTest {
	public static void main(String[] args) {
		SupportFormJDBCDAOIm dao = new SupportFormJDBCDAOIm();
		SupportFormHDAOIm hdao = new SupportFormHDAOIm(HibernateUtil.getSessionFactory());
		
		//Hibernate DAO新增
		SupportFormVO sfvoH = new SupportFormVO();
		sfvoH.setRealName("ken");
		sfvoH.setSupportFormCategoryId(2);
		sfvoH.setReplyEmail("aaa@aaa.com");
		sfvoH.setFormTitle("test");
		sfvoH.setFormContext("abcdefgs");
		sfvoH.setFormStatus((byte) 1);
		sfvoH.setFormSource((byte)1);
		sfvoH.setFormSubmitter("test");
		hdao.insert(sfvoH);

	 
//		SupportFormVO supportFormVO1 = new SupportFormVO();
//		supportFormVO1.setRealName("ken");
//		supportFormVO1.setSupportFormCategoryId(2);
//		supportFormVO1.setReplyEmail("aaa@aaa.com");
//		supportFormVO1.setFormTitle("test");
//		supportFormVO1.setFormContext("abcdefgs");
//		supportFormVO1.setFormStatus((byte) 1);
//		supportFormVO1.setFormSource("test");
//		supportFormVO1.setFormSubmitter("test");
//		dao.insert(supportFormVO1);


//		SupportFormVO supportFormVO2 = new SupportFormVO();
//		supportFormVO2.setRealName("amy");
//		supportFormVO2.setSupportFormCategoryId(2);
//		supportFormVO2.setReplyEmail("aaa@aaa.com");
//		supportFormVO2.setFormTitle("test");
//		supportFormVO2.setFormContext("abcdefgs");
//		supportFormVO2.setFormStatus((byte) 1);
//		supportFormVO2.setFormSource("test");
//		supportFormVO2.setFormResponder(1);
//		supportFormVO2.setFormNo(1);
//		dao.update(supportFormVO2);

//		dao.delete(3);


//		SupportFormVO supportFormVO3 = dao.findByPrimaryKey(1);
//		System.out.print(supportFormVO3);
//		System.out.println("---------------------");


//		List<SupportFormVO> list = dao.getAll();
//		for (SupportFormVO aSupportForm : list) {
//			System.out.print(aSupportForm);
//			System.out.println();
//		}

	}

}
