package com.cooklab.article_reaction.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class ArticleReactionJDBCDAOIm implements ArticleReactionDAO{
	String driver = Util.DRIVER;
	String url =Util.URL;
	String userid =Util.USER;
	String passwd =Util.PASSWORD;
	
	private static final String INSERT_STMT = 
			"INSERT INTO article_reaction(member_id,article_no,statuts,created_timestamp) VALUES (?, ?, ?,now())";
		private static final String GET_ALL_STMT = 
			"SELECT article_reaction_no,member_id,article_no,statuts,created_timestamp FROM article_reaction order by article_reaction_no";
		private static final String GET_ONE_STMT = 
			"SELECT article_reaction_no,member_id,article_no,statuts,created_timestamp FROM article_reaction where article_reaction_no = ?";
		private static final String DELETE = 
			"DELETE FROM article_reaction where article_reaction_no = ?";
		private static final String UPDATE = 
			"UPDATE article_reaction set member_id=?, article_no=?, statuts=? created_timestamp=now() where article_reaction_no = ? ";

		@Override
		public void insert(ArticleReactionVO ArticleReactionVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setInt(1, ArticleReactionVO.getMemberId());
				pstmt.setInt(2, ArticleReactionVO.getArticleNo());
				pstmt.setByte(3, ArticleReactionVO.getStatuts());


				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void update(ArticleReactionVO ArticleReactionVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, ArticleReactionVO.getMemberId());
				pstmt.setInt(2, ArticleReactionVO.getArticleNo());
				pstmt.setByte(3, ArticleReactionVO.getStatuts());
				pstmt.setInt(4, ArticleReactionVO.getArticleReactionNo());


				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public void delete(Integer article_reaction_no) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, article_reaction_no);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public ArticleReactionVO findByPrimaryKey(Integer article_reaction_no) {

			ArticleReactionVO ArticleReactionVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, article_reaction_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empArticleReactionVO 也稱為 Domain objects
					ArticleReactionVO = new ArticleReactionVO();
					ArticleReactionVO.setMemberId(rs.getInt("member_id"));
					ArticleReactionVO.setArticleNo(rs.getInt("article_no"));
					ArticleReactionVO.setStatuts(rs.getByte("statuts"));
					ArticleReactionVO.setArticleReactionNo(rs.getInt("article_reaction_no"));
					ArticleReactionVO.setCreatedTimestamp(rs.getDate("created_timestamp"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return ArticleReactionVO;
		}
//===========================findByPrimaryKey結束==============================================
		@Override
		public List<ArticleReactionVO> getAll() {
			List<ArticleReactionVO> list = new ArrayList<ArticleReactionVO>();
			ArticleReactionVO ArticleReactionVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empArticleReactionVO 也稱為 Domain objects
					ArticleReactionVO = new ArticleReactionVO();
					ArticleReactionVO.setArticleReactionNo(rs.getInt("article_reaction_no"));
					ArticleReactionVO.setMemberId(rs.getInt("member_id"));
					ArticleReactionVO.setArticleNo(rs.getInt("article_no"));
					ArticleReactionVO.setStatuts(rs.getByte("statuts"));
					ArticleReactionVO.setCreatedTimestamp(rs.getDate("created_timestamp"));

					list.add(ArticleReactionVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

			ArticleReactionJDBCDAOIm dao = new ArticleReactionJDBCDAOIm();

			// 新增
//			ArticleSubReactionVO ArticleReactionVO1 = new ArticleSubReactionVO();
//			ArticleReactionVO1.setMemberId(Integer.valueOf(1));
//			ArticleReactionVO1.setArticleNo(Integer.valueOf(1));
//			ArticleReactionVO1.setStatuts(Byte.valueOf((byte)1));
//			dao.insert(ArticleReactionVO1);

//			// 修改
//		    ArticleSubReactionVO ArticleReactionVO2 = new ArticleSubReactionVO();
//			ArticleReactionVO2.setArticleReactionNo(Integer.valueOf(4));
//			ArticleReactionVO2.setMemberId(2);
//			ArticleReactionVO2.setArticleNo(2);
//			ArticleReactionVO2.setStatuts(Byte.valueOf((byte)0));
//
//			dao.update(ArticleReactionVO2);
////
//////			// 刪除
//			dao.delete(3);
//////
//////			// 查詢
//			ArticleSubReactionVO ArticleReactionVO3 = dao.findByPrimaryKey(1);
//			System.out.print(ArticleReactionVO3.getArticleReactionNo() + ",");
//			System.out.print(ArticleReactionVO3.getMemberId() + ",");
//			System.out.print(ArticleReactionVO3.getArticleNo() + ",");
//			System.out.print(ArticleReactionVO3.getStatuts() + ",");
//			System.out.println(ArticleReactionVO3.getCreatedTimestamp() + " ");
//			System.out.println("---------------------");
//////
//////			// 查詢
//			List<ArticleSubReactionVO> list = dao.getAll();
//			for (ArticleSubReactionVO aArticleReactionVO : list) {
//				System.out.print(aArticleReactionVO.getArticleReactionNo() + ",");
//				System.out.print(aArticleReactionVO.getMemberId() + ",");
//				System.out.print(aArticleReactionVO.getArticleNo() + ",");
//				System.out.print(aArticleReactionVO.getMemberId() + ",");
//				System.out.print(aArticleReactionVO.getStatuts() + ",");
//				System.out.println(aArticleReactionVO.getCreatedTimestamp() + " ");
//
//				System.out.println("---------------------");
//			}
		}
}
