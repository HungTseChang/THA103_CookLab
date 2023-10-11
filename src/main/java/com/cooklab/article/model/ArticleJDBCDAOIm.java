package com.cooklab.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class ArticleJDBCDAOIm implements ArticleDAO {

	private static final String INSERT_STMT = "INSERT INTO article (article_category,article_title,member_id,article_status,article_content,article_count,view_count, last_edit_timestamp ) VALUES (?, ?, ?, ?, ?, ?, ?,now())";
	private static final String GET_ALL_STMT = "SELECT article_no,article_category,article_title,member_id,article_status,article_content,article_count,view_count,last_edit_timestamp,created_timestamp FROM article order by article_no";
	private static final String GET_ONE_STMT = "SELECT article_no,article_category,article_title,member_id,article_status,article_content,article_count,view_count,last_edit_timestamp,created_timestamp FROM article where article_no = ?";
	private static final String DELETE = "DELETE FROM article where article_no = ?";
	private static final String UPDATE = "UPDATE article set article_category=?, article_title=?, member_id=?, article_status=?, article_content=?, article_count=?, view_count=? , last_edit_timestamp=now()  where article_no = ?";

	@Override
	public void insert(ArticleVO ArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ArticleVO.getArticleCategory());
			pstmt.setString(2, ArticleVO.getArticleTitle());
			pstmt.setInt(3, ArticleVO.getMemberId());
			pstmt.setByte(4, ArticleVO.getArticleStatus());
			pstmt.setString(5, ArticleVO.getArticleContent());
			pstmt.setInt(6, ArticleVO.getArticleCount());
			pstmt.setInt(7, ArticleVO.getViewCount());

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
	@Override
	public void update(ArticleVO ArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ArticleVO.getArticleCategory());
			pstmt.setString(2, ArticleVO.getArticleTitle());
			pstmt.setInt(3, ArticleVO.getMemberId());
			pstmt.setByte(4, ArticleVO.getArticleStatus());
			pstmt.setString(5, ArticleVO.getArticleContent());
			pstmt.setInt(6, ArticleVO.getArticleCount());
			pstmt.setInt(7, ArticleVO.getViewCount());
			pstmt.setInt(8, ArticleVO.getArticleNo());

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
	@Override
	public void delete(Integer articleNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleNo);

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
	@Override
	public ArticleVO findByPrimaryKey(Integer articleNo) {

		ArticleVO ArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empArticleVO 也稱為 Domain objects
				ArticleVO = new ArticleVO();
				ArticleVO.setArticleNo(rs.getInt("article_no"));
				ArticleVO.setArticleCategory(rs.getInt("article_category"));
				ArticleVO.setArticleTitle(rs.getString("article_title"));
				ArticleVO.setMemberId(rs.getInt("member_id"));
				ArticleVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				ArticleVO.setArticleStatus(rs.getByte("article_status"));
				ArticleVO.setArticleContent(rs.getString("article_content"));
				ArticleVO.setArticleCount(rs.getInt("article_count"));
				ArticleVO.setViewCount(rs.getInt("view_count"));
				ArticleVO.setLastEditTimestamp(rs.getTimestamp("last_edit_timestamp"));
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
		return ArticleVO;
	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO ArticleVO = null;

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
				ArticleVO = new ArticleVO();
				ArticleVO.setArticleNo(rs.getInt("article_no"));
				ArticleVO.setArticleCategory(rs.getInt("article_category"));
				ArticleVO.setArticleTitle(rs.getString("article_title"));
				ArticleVO.setMemberId(rs.getInt("member_id"));
				ArticleVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				ArticleVO.setArticleStatus(rs.getByte("article_status"));
				ArticleVO.setArticleContent(rs.getString("article_content"));
				ArticleVO.setArticleCount(rs.getInt("article_count"));
				ArticleVO.setViewCount(rs.getInt("view_count"));
				ArticleVO.setLastEditTimestamp(rs.getTimestamp("last_edit_timestamp"));

				list.add(ArticleVO); // Store the row in the list
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

		ArticleJDBCDAOIm dao = new ArticleJDBCDAOIm();

		// 新增
		ArticleVO ArticleVO1 = new ArticleVO();
		ArticleVO1.setArticleCategory(1);
		ArticleVO1.setArticleTitle("今天大家吃甚麼?");
		ArticleVO1.setMemberId(Integer.valueOf(1));
		ArticleVO1.setArticleStatus(Byte.valueOf((byte) 1));
		ArticleVO1.setArticleContent("今年好霉氣全無財錦進門養豬各各大老鼠各各瘟做酒缸缸好做醋滴滴酸");
		ArticleVO1.setArticleCount(Integer.valueOf(0));
		ArticleVO1.setViewCount(Integer.valueOf(0));
		dao.insert(ArticleVO1);

////			// 修改
//		    ArticleVO ArticleVO2 = new ArticleVO();
//			ArticleVO2.setArticleNo(Integer.valueOf(2));
//			ArticleVO2.setArticleCategory("閒聊版");
//			ArticleVO2.setArticleTitle("明天大家吃甚麼11?");
//			ArticleVO2.setMemberId(Integer.valueOf(1) );
//			ArticleVO2.setArticleStatus(Byte.valueOf((byte) 0)) ;
//			ArticleVO2.setArticleContent("下雨天留客天留我不留") ;
//			ArticleVO2.setArticleCount(Integer.valueOf(10)) ;
//			ArticleVO2.setViewCount(Integer.valueOf(10)) ;
//			dao.update(ArticleVO2);
//
//////			// 刪除
//			dao.delete(3);
//
//
//////
//////			// 查詢
//			ArticleVO ArticleVO3 = dao.findByPrimaryKey(2);
//			System.out.print(ArticleVO3.getArticleNo() + ",");
//			System.out.print(ArticleVO3.getArticleCategory() + ",");
//			System.out.print(ArticleVO3.getArticleTitle() + ",");
//			System.out.print(ArticleVO3.getMemberId() + ",");
//			System.out.print(ArticleVO3.getArticleStatus() + ",");
//			System.out.print(ArticleVO3.getArticleContent() + ",");
//			System.out.print(ArticleVO3.getArticleCount()+ ",");
//			System.out.print(ArticleVO3.getViewCount()+",");
//			System.out.print(ArticleVO3.getLastEditTimestamp() + ",");
//			System.out.println(ArticleVO3.getCreatedTimestamp() + ",");
//
//			System.out.println("---------------------");
//////
//////			// 查詢
		List<ArticleVO> list = dao.getAll();
		for (ArticleVO aArticleVO : list) {
			System.out.print(aArticleVO.getArticleNo() + ",");
			System.out.print(aArticleVO.getArticleCategory() + ",");
			System.out.print(aArticleVO.getArticleTitle() + ",");
			System.out.print(aArticleVO.getMemberId() + ",");
			System.out.print(aArticleVO.getArticleStatus() + ",");
			System.out.print(aArticleVO.getArticleContent() + ",");
			System.out.print(aArticleVO.getArticleCount() + ",");
			System.out.print(aArticleVO.getViewCount() + ",");
			System.out.print(aArticleVO.getLastEditTimestamp() + ",");
			System.out.println(aArticleVO.getCreatedTimestamp() + ",");
			System.out.println("---------------------");
		}
	}

}
