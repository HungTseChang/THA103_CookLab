package com.cooklab.recipe.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.cooklab.util.HibernateUtil;

public class RecipeTest {
	public static void main(String[] args) throws IOException {
//		RecipeJDBCDAOlm dao = new RecipeJDBCDAOlm();
//=======================insert=======================
//		RecipeVO vo = new RecipeVO(null,"超好吃大便", 1, getPictureByteArray("519577.jpg"), "222", "333", "444", (byte)0, 0, 0, (byte)1, null, null);
//		dao.insert(vo);
//=======================update=======================
//		RecipeVO vo = new RecipeVO(1, 2, null, "²��update", "�ɥR����update", "�a��update", (byte)1, 1, 1, (byte)2, null, null);		
//		dao.update(vo);	
//=======================delete=======================		
//		dao.delete(1);
//=======================findByPrimaryKey=============
//		System.out.println(dao.findByPrimaryKey(1));
//	
//=======================getAll=======================
//		List<RecipeVO> recipeList;
//		recipeList = dao.getAll();
//		for (RecipeVO recipe : recipeList) {
//			System.out.println(recipe);
//		}
//==========================Hibernate============================
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			//新增
//			RecipeVO vo = new RecipeVO(null, 1, "超好吃大便",getPictureByteArray("src/main/resources/img/images.jpg"), "222", "333", "444", (byte)0, 0, 0, (byte)1, null, null);			
//			session.saveOrUpdate(vo);
			//更新
//			RecipeVO vo = new RecipeVO(1, 1, "超好吃大便2",getPictureByteArray("src/main/resources/img/images.jpg"), "222", "333", "444", (byte)0, 0, 0, (byte)1, null, null);			
//			session.saveOrUpdate(vo);
			//刪除
//			RecipeVO vo = session.get(RecipeVO.class,1);
//			if(vo !=null) {
//				session.delete(vo);
//			}
			//查詢(多筆)
//			List<RecipeVO> list = session.createQuery("from RecipeVO ",RecipeVO.class).list();
//			System.out.println(list);
			//查詢(單筆)
//			RecipeVO vo = session.createQuery("from RecipeVO where  recipeNo = 1", RecipeVO.class).uniqueResult();
//			System.out.println(vo);
			//測試映射
//			Query<RecipeVO> vo = session.createQuery("select new RecipeVO(lastEditTimestamp) from RecipeVO", RecipeVO.class);
//			List<RecipeVO> list = vo.list();
//			System.out.println(list);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
		byte[] buffer = fis.readAllBytes();
		fis.close();
		return buffer;
	}
}
