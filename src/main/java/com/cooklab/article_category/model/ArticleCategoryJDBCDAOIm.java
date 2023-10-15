package com.cooklab.article_category.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.article_category.model.ArticleCategoryDAO;
import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.util.Util;

public class ArticleCategoryJDBCDAOIm implements ArticleCategoryDAO{
	private static final String INSERT_STMT = "INSERT INTO article_category (article_category) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT article_category_no,article_category,category_status,created_timestamp FROM article_category ORDER BY article_category_no";
	private static final String GET_ONE_STMT = "SELECT article_category_no,article_category,category_status,created_timestampFROM article_category where article_category_no = ?";
	private static final String DELETE = "DELETE FROM article_category where article_category_no = ?";
	private static final String UPDATE = "UPDATE article_category set category_status=?   where article_category_no = ?";

	public void insert(ArticleCategoryVO artVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, artVO.getArticleCategory());
	

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//============================insert完結=======================================
	public void update(ArticleCategoryVO artVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setByte(1, artVO.getCategoryStatus());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//=================================update結束========================================
	
	public void delete(Integer articleCategoryNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleCategoryNo);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

//===============================delete結束======================================
	
	public ArticleCategoryVO findByPrimaryKey(Integer articleCategoryNo) {

		ArticleCategoryVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleCategoryNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				artVO = new ArticleCategoryVO();
				artVO.setArticleCategoryNo(rs.getInt("article_category_no"));
				artVO.setArticleCategory(rs.getString("article_category"));
				artVO.setCategoryStatus(rs.getByte("category_status"));
				artVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return artVO;
	}

//===========================findByPrimaryKey結束==============================================

	public List<ArticleCategoryVO> getAll() {
		List<ArticleCategoryVO> list = new ArrayList<ArticleCategoryVO>();
		ArticleCategoryVO artVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empArticleVO 也稱為 Domain objects
				artVO = new ArticleCategoryVO();
				artVO.setArticleCategoryNo(rs.getInt("article_category_no"));
				artVO.setArticleCategory(rs.getString("article_category"));
				artVO.setCategoryStatus(rs.getByte("category_status"));
				artVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

				list.add(artVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

//===================================getAll結束========================================
	public static void main(String[] args) {

//		ArticleJDBCDAOIm dao = new ArticleJDBCDAOIm();

	
//////			// 查詢
//		List<ArticleVO> list = dao.getAll();
//		for (ArticleVO aArticleVO : list) {
//			System.out.print(aArticleVO.getArticleNo() + ",");
//			System.out.print(aArticleVO.getArticleCategory() + ",");
//			System.out.print(aArticleVO.getArticleTitle() + ",");
//			System.out.print(aArticleVO.getMemberId() + ",");
//			System.out.print(aArticleVO.getArticleStatus() + ",");
//			System.out.print(aArticleVO.getArticleContent() + ",");
//			System.out.print(aArticleVO.getArticleCount() + ",");
//			System.out.print(aArticleVO.getViewCount() + ",");
//			System.out.print(aArticleVO.getLastEditTimestamp() + ",");
//			System.out.println(aArticleVO.getCreatedTimestamp() + ",");
//			System.out.println("---------------------");
		
	}
}
