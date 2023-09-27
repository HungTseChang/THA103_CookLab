package com.cooklab.article_sub_reaction.model;

import com.cooklab.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleReactionJDBCDAOIm implements ArticleReactionDAO {

	private static final String INSERT_STMT = "INSERT INTO article_sub_reaction(member_id,article_sub_no,statuts,created_timestamp) VALUES (?, ?, ?,now())";
	private static final String GET_ALL_STMT = "SELECT article_sub_reaction_no,member_id,article_sub_no,statuts,created_timestamp FROM article_sub_reaction order by article_sub_reaction_no";
	private static final String GET_ONE_STMT = "SELECT article_sub_reaction_no,member_id,article_sub_no,statuts,created_timestamp FROM article_sub_reaction where article_sub_reaction_no = ?";
	private static final String DELETE = "DELETE FROM article_sub_reaction where article_sub_reaction_no = ?";
	private static final String UPDATE = "UPDATE article_sub_reaction set member_id=?, article_sub_no=?, statuts=?, created_timestamp=now() where article_sub_reaction_no = ? ";

	@Override
	public void insert(ArticleReactionVO ArticleReactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, ArticleReactionVO.getMemberId());
			pstmt.setInt(2, ArticleReactionVO.getArticleSubNo());
			pstmt.setByte(3, ArticleReactionVO.getStatuts());

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
	public void update(ArticleReactionVO ArticleReactionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ArticleReactionVO.getMemberId());
			pstmt.setInt(2, ArticleReactionVO.getArticleSubNo());
			pstmt.setByte(3, ArticleReactionVO.getStatuts());
			pstmt.setInt(4, ArticleReactionVO.getArticleSubReactionNo());

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
	public void delete(Integer article_sub_reaction_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_sub_reaction_no);

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
	public ArticleReactionVO findByPrimaryKey(Integer article_sub_reaction_no) {

		ArticleReactionVO ArticleReactionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, article_sub_reaction_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empArticleReactionVO 也稱為 Domain objects
				ArticleReactionVO = new ArticleReactionVO();
				ArticleReactionVO.setMemberId(rs.getInt("member_id"));
				ArticleReactionVO.setArticleSubNo(rs.getInt("article_sub_no"));
				ArticleReactionVO.setStatuts(rs.getByte("statuts"));
				ArticleReactionVO.setArticleSubReactionNo(rs.getInt("article_sub_reaction_no"));
				ArticleReactionVO.setCreatedTimestamp(rs.getDate("created_timestamp"));
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

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empArticleReactionVO 也稱為 Domain objects
				ArticleReactionVO = new ArticleReactionVO();
				ArticleReactionVO.setArticleSubReactionNo(rs.getInt("article_sub_reaction_no"));
				ArticleReactionVO.setMemberId(rs.getInt("member_id"));
				ArticleReactionVO.setArticleSubNo(rs.getInt("article_sub_no"));
				ArticleReactionVO.setStatuts(rs.getByte("statuts"));
				ArticleReactionVO.setCreatedTimestamp(rs.getDate("created_timestamp"));

				list.add(ArticleReactionVO); // Store the row in the list
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

		ArticleReactionJDBCDAOIm dao = new ArticleReactionJDBCDAOIm();

		// 新增
//			ArticleReactionVO ArticleReactionVO1 = new ArticleReactionVO();
//			ArticleReactionVO1.setMemberId(Integer.valueOf(1));
//			ArticleReactionVO1.setArticleSubNo(Integer.valueOf(1));
//			ArticleReactionVO1.setStatuts(Byte.valueOf((byte)1));
//			dao.insert(ArticleReactionVO1);

//			// 修改
		ArticleReactionVO ArticleReactionVO2 = new ArticleReactionVO();
		ArticleReactionVO2.setArticleSubReactionNo(Integer.valueOf(2));
		ArticleReactionVO2.setMemberId(2);
		ArticleReactionVO2.setArticleSubNo(1);
		ArticleReactionVO2.setStatuts(Byte.valueOf((byte) 0));

		dao.update(ArticleReactionVO2);
////
//////			// 刪除
//			dao.delete(3);
//////
//////			// 查詢
//			ArticleReactionVO ArticleReactionVO3 = dao.findByPrimaryKey(1);
//			System.out.print(ArticleReactionVO3.getArticleSubReactionNo() + ",");
//			System.out.print(ArticleReactionVO3.getMemberId() + ",");
//			System.out.print(ArticleReactionVO3.getArticleSubNo() + ",");
//			System.out.print(ArticleReactionVO3.getStatuts() + ",");
//			System.out.println(ArticleReactionVO3.getCreatedTimestamp() + " ");
//			System.out.println("---------------------");
//////
//////			// 查詢
		List<ArticleReactionVO> list = dao.getAll();
		for (ArticleReactionVO aArticleReactionVO : list) {
			System.out.print(aArticleReactionVO.getArticleSubReactionNo() + ",");
			System.out.print(aArticleReactionVO.getMemberId() + ",");
			System.out.print(aArticleReactionVO.getArticleSubNo() + ",");
			System.out.print(aArticleReactionVO.getMemberId() + ",");
			System.out.print(aArticleReactionVO.getStatuts() + ",");
			System.out.println(aArticleReactionVO.getCreatedTimestamp() + " ");

			System.out.println("---------------------");
		}
	}
}
