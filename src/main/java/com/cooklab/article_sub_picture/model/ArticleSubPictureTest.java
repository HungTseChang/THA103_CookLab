package com.cooklab.article_sub_picture.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;


public class ArticleSubPictureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			/////////新增
//			ArticleSubPictureVO ArticleSubPictureVO = new ArticleSubPictureVO();
//			ArticleSubPictureVO.setArticleSubNo(1);
//			File imageFile = new File("src/main/resources/img/images/butterfly.png");
//			FileInputStream inputStream;
//
//				inputStream = new FileInputStream(imageFile);  /// inputStream為imageFile的資料流類型
//	            byte[] imageBytes = new byte[(int) imageFile.length()];///imageBytes為byte[] 其長度(位元數)等同於imageFile的大小
//	            inputStream.read(imageBytes);  ///用inputStream的方法read()將其檔案輸入致imageBytes
//	            inputStream.close();
//               ArticleSubPictureVO.setPicture(imageBytes);
//			
//           	session.save(ArticleSubPictureVO);
 ////=========================== 修改====================================
			 
//			ArticleSubPictureVO ArticleSubPictureVO1  = session.get(ArticleSubPictureVO.class, 1);
//           	if(ArticleSubPictureVO1 != null) {
//           		ArticleSubPictureVO1.setArticleSubNo(4);
//           	}
			
//	========================刪除===========================
           	
        	ArticleSubPictureVO ArticleSubPictureVO2 = session.get(ArticleSubPictureVO.class, 6);
          	if(ArticleSubPictureVO2 != null) {
           		session.delete(ArticleSubPictureVO2);
           	}
           	
           	
//           	=============================================		
			////查詢
	List<ArticleSubPictureVO> list2 = session.createQuery("from ArticleSubPictureVO").list();
    
	for(ArticleSubPictureVO arry:list2) {
		System.out.println("ArticleSubPictureNo: "+arry.getArticleSubPictureNo());
		System.out.println("ArticleSubNo:  "+arry.getArticleSubNo());
		System.out.println("Picture:   "+arry.getPicture());

		
	}
//	System.out.println(list2);

			
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