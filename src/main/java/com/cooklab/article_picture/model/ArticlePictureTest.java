package com.cooklab.article_picture.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class ArticlePictureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			/////////新增
//			ArticlePictureVO ArticlePictureVO = new ArticlePictureVO();
//			ArticlePictureVO.setArticleNo(1);
//			File imageFile = new File("src/main/resources/img/images/butterfly.png");
//			FileInputStream inputStream;
//
//				inputStream = new FileInputStream(imageFile);  /// inputStream為imageFile的資料流類型
//	            byte[] imageBytes = new byte[(int) imageFile.length()];///imageBytes為byte[] 其長度(位元數)等同於imageFile的大小
//	            inputStream.read(imageBytes);  ///用inputStream的方法read()將其檔案輸入致imageBytes
//	            inputStream.close();
//               ArticlePictureVO.setPicture(imageBytes);
//			
//           	session.save(ArticlePictureVO);
 ////=========================== 修改====================================
			 
//           	ArticlePictureVO ArticlePictureVO1  = session.get(ArticlePictureVO.class, 1);
//           	if(ArticlePictureVO1 != null) {
//           		ArticlePictureVO1.setArticleNo(4);
//           	}
			
//	========================刪除===========================
           	
//        	ArticlePictureVO ArticlePictureVO2 = session.get(ArticlePictureVO.class, 3);
//          	if(ArticlePictureVO2 != null) {
//           		session.delete(ArticlePictureVO2);
//           	}
//           	
           	
//           	=============================================		
			////查詢
//	List<ArticlePictureVO> list2 = session.createQuery("from ArticlePictureVO").list();
//    System.out.println(list2);

			
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	

	}

}
