package com.cooklab.recipe.model;

import java.time.LocalTime;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecipeTest {
	public static void main(String[] args) throws IOException {
		RecipeJDBCDAOlm dao = new RecipeJDBCDAOlm();
//=======================insert=======================
		RecipeVO vo = new RecipeVO(null,"超好吃大便", 1, getPictureByteArray("519577.jpg"), "222", "333", "444", (byte)0, 0, 0, (byte)1, null, null);
		dao.insert(vo);
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
//======================================================
		
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
