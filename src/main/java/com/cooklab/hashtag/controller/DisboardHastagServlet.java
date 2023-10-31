package com.cooklab.hashtag.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import com.cooklab.admins.model.AdminsVO;
import com.cooklab.hashtag.model.*;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;
@WebServlet("/DisboardHastagServlet")
public class DisboardHastagServlet extends HttpServlet{
private List<HashtagVO> thelist;
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setContentType("text/html; charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			String forwardPath = "";
			switch (action) {
			case "getHastag":
				forwardPath = getHastag(req, res);
				break;
			case "insert":
				forwardPath = insert(req, res);
				break;
			case "update":
				forwardPath = update(req, res);
				break;
			case "delete":
				forwardPath = delete(req, res);
				break;
			default:
				forwardPath = "/dashboard/hashtag/WCC_hashtag.jsp";
			}
//			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);

		}





		private String insert(HttpServletRequest req, HttpServletResponse res) {
			HashtagSeriveFake HashtagSeriveFake = new HashtagSeriveFake();
//			Integer permissionNo = Integer.valueOf(req.getParameter("permissionNo"));
			String hashtagName = req.getParameter("hashtagName").trim();
			String categoryTags = req.getParameter("categoryTags").trim();
			Byte officialTags =Byte.valueOf(req.getParameter("officialTags"));
			Timestamp a =new Timestamp(System.currentTimeMillis());
//			Optional <HashtagVO> result1 = this.thelist.stream().filter(e->e.getHashtagName().equals(hashtagName)).findFirst();
//			 if(result1.isPresent()) {
//				 String error = new Gson().toJson("新增失敗，標籤名稱重複".trim());
//					req.setAttribute("error",error );			
//					return  "/dashboard/hashtag/WCC_hashtag.jsp";
//			 }
			 HashtagVO HashtagVO = new HashtagVO();
			 HashtagVO.setHashtagName(hashtagName);
			 HashtagVO.setCategoryTags(categoryTags);
			 HashtagVO.setOfficialTags(officialTags);
			 HashtagVO.setSearchCount(0);
			 HashtagVO.setUseCount(0);
			 HashtagVO.setCreatedTimestamp(a);
			 HashtagSeriveFake.add(HashtagVO);			
			return "/dashboard/hashtag/WCC_hashtag.jsp";
		}

		private String getHastag(HttpServletRequest req, HttpServletResponse res) {
			HashtagSeriveFake HashtagSeriveFake = new HashtagSeriveFake();
			List<HashtagVO> list = HashtagSeriveFake.getAll();
			List<HashtagVOFake> list1 = new ArrayList<HashtagVOFake>();
			int length = list.size();
			for(int i = 0 ; i<length;i++) {
				HashtagVOFake HashtagVOFake = new HashtagVOFake(list.get(i));
				list1.add(HashtagVOFake);
			}
			String json = new Gson().toJson(list1);
			System.out.println(json);
			req.setAttribute("json", json);
			
			this.thelist = list;
			return "/dashboard/hashtag/WCC_hashtag.jsp";
		}
//		=====================================UPDATE====================================================
		private String update(HttpServletRequest req, HttpServletResponse res) {
			HashtagSeriveFake HashtagSeriveFake = new HashtagSeriveFake();
			Integer hashtagNO = Integer.valueOf(req.getParameter("hashtagNO"));
			String hashtagName = req.getParameter("hashtagName").trim();
			HashtagVO HashtagVO;	
			if(this.thelist ==null) {			
				HashtagVO = HashtagSeriveFake.findbyNO(hashtagNO);
			}
			
			 HashtagVO	=	this.thelist.stream().filter(e->e.getHashtagNO().equals(hashtagNO)).findFirst().get();
//			 this.thelist.remove(HashtagVO);
//				Optional <HashtagVO> result1 = this.thelist.stream().filter(e->e.getHashtagName().equals(hashtagName)).findFirst();
//			 if(result1.isPresent()) {
//				 String error = new Gson().toJson("新增失敗，標籤名稱重複".trim());
//					req.setAttribute("error",error );	
//					 this.thelist.add(HashtagVO);
//						return  "/dashboard/hashtag/WCC_hashtag.jsp";

//			 }
			 this.thelist.add(HashtagVO);
			String categoryTags = req.getParameter("categoryTags").trim();
			Integer searchCount =  Integer.valueOf(req.getParameter("searchCount"));
			Integer useCount =  Integer.valueOf(req.getParameter("useCount"));
			Byte officialTags =Byte.valueOf(req.getParameter("officialTags"));
			Timestamp a =new Timestamp(System.currentTimeMillis());
			
			
			
	//============================================================================
			HashtagVO.setHashtagName(hashtagName);
			HashtagVO.setCategoryTags(categoryTags);
			HashtagVO.setOfficialTags(officialTags);
			HashtagSeriveFake.update(HashtagVO);			
			return  "/dashboard/hashtag/WCC_hashtag.jsp";
		}
		
		private String delete(HttpServletRequest req, HttpServletResponse res) {
			Integer hashtagNO = Integer.valueOf(req.getParameter("hashtagNO"));
			HashtagSeriveFake HashtagSeriveFake = new HashtagSeriveFake();
			HashtagSeriveFake.delete(hashtagNO);
			return  "/dashboard/hashtag/WCC_hashtag.jsp";
		}
		

		
		private class HashtagVOFake{
			private Integer hashtagNO; // 標籤編號(PK)
			private String hashtagName; // 標籤名稱
			private String categoryTags; // 標籤種類
			private Integer searchCount; // 搜尋次數
			private Integer useCount; // 使用次數
			private Byte officialTags; // 官方標籤
			private Timestamp createdTimestamp; // 建立時間
			
			
			

			public HashtagVOFake(HashtagVO HashtagVO) {
				super();
				this.hashtagNO = HashtagVO.getHashtagNO();
				this.hashtagName = HashtagVO.getHashtagName();
				this.categoryTags = HashtagVO.getCategoryTags();
				this.searchCount = HashtagVO.getSearchCount();
				this.useCount = HashtagVO.getUseCount();
				this.officialTags = HashtagVO.getOfficialTags();
				this.createdTimestamp = HashtagVO.getCreatedTimestamp();
			}
			public Integer getHashtagNO() {
				return hashtagNO;
			}
			public void setHashtagNO(Integer hashtagNO) {
				this.hashtagNO = hashtagNO;
			}
			public String getHashtagName() {
				return hashtagName;
			}
			public void setHashtagName(String hashtagName) {
				this.hashtagName = hashtagName;
			}
			public String getCategoryTags() {
				return categoryTags;
			}
			public void setCategoryTags(String categoryTags) {
				this.categoryTags = categoryTags;
			}
			public Integer getSearchCount() {
				return searchCount;
			}
			public void setSearchCount(Integer searchCount) {
				this.searchCount = searchCount;
			}
			public Integer getUseCount() {
				return useCount;
			}
			public void setUseCount(Integer useCount) {
				this.useCount = useCount;
			}
			public Byte getOfficialTags() {
				return officialTags;
			}
			public void setOfficialTags(Byte officialTags) {
				this.officialTags = officialTags;
			}
			public Timestamp getCreatedTimestamp() {
				return createdTimestamp;
			}
			public void setCreatedTimestamp(Timestamp createdTimestamp) {
				this.createdTimestamp = createdTimestamp;
			}
			
		}
		
		
		private class HashtagSeriveFake {			
		private HashtagDAO dao;

		public HashtagSeriveFake() {
			dao = new HashtagHDAOIm(HibernateUtil.getSessionFactory());
		}	
		public boolean add(HashtagVO HashtagVO) {
		

			int a =dao.insert(HashtagVO);
				
				return (a==0);
			}
			
		public boolean update(HashtagVO HashtagVO) {
			boolean a =	dao.update(HashtagVO);
				return a;
			}
		public boolean delete(Integer HashtagNO) {
			boolean a =	dao.delete(HashtagNO);
			return a;
	}
		
		public List<HashtagVO> getAll() {
		return dao.getAll();
	}
		
		public HashtagVO findbyName(String hashtagName) {
			return dao.findByHashtagName(hashtagName);
		}
		public HashtagVO findbyNO(Integer hashtagNO) {
			return dao.findByPrimaryKey(hashtagNO);
		}
		
		}
}


