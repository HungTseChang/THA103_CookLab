package com.cooklab.article_collection.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.Util.*;
public class ArticleCollectionJDBCDAOIm implements ArticleCollectionDAO {

	private static final String INSERT_STMT = "INSERT INTO article_collection (article_no ,member_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT article_collection_no  , member_id , created_timestamp FROM article_collection order by article_collection_no ";
	private static final String GET_ONE_STMT = "SELECT article_collection_no  , member_id , created_timestamp FROM article_collection where article_collection_no  = ?";
	private static final String DELETE = "DELETE FROM article_collection where article_collection_no = ?";
	private static final String UPDATE = "UPDATE article_collection set article_collection_no=?, member_id=? where article_collection_no = ?";

	// 新增===========================================================================================================

	@Override
	public void insert(ArticleCollectionVO articleCollectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleCollectionVO.getArticleNo());
			pstmt.setInt(2, articleCollectionVO.getMemberId());

			pstmt.executeUpdate();

			// Handle any Util.DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database Util.DRIVER. " + e.getMessage());
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

	// 修改=================================================================
	@Override
	public void update(ArticleCollectionVO articleCollectionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleCollectionVO.getArticleNo());
			pstmt.setInt(2, articleCollectionVO.getMemberId());
			pstmt.setInt(3, articleCollectionVO.getArticleCollectionNo());

			pstmt.executeUpdate();

			// Handle any Util.DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database Util.DRIVER. " + e.getMessage());
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

	// 刪除===========================================================
	@Override
	public void delete(Integer articleCollectionNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleCollectionNo);

			pstmt.executeUpdate();

			// Handle any Util.DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database Util.DRIVER. " + e.getMessage());
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

	// 用主鍵查詢===========================================================================================================
	@Override
	public ArticleCollectionVO findByPrimaryKey(Integer articleCollectionNo) {

		ArticleCollectionVO acVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleCollectionNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				acVO = new ArticleCollectionVO();

				acVO.setArticleCollectionNo(rs.getInt("article_collection_no"));
				acVO.setArticleNo(rs.getInt("article_no"));
				acVO.setMemberId(rs.getInt("member_id"));
				acVO.setCreateTimestamp(rs.getTimestamp("created_timestamp"));
			}

			// Handle any Util.DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database Util.DRIVER. " + e.getMessage());
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
		return acVO;
	}

	//查詢所有===========================================================================================================
	@Override
	public List<ArticleCollectionVO> getAll() {
		List<ArticleCollectionVO> list = new ArrayList<ArticleCollectionVO>();
		ArticleCollectionVO articleCollectionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				articleCollectionVO = new ArticleCollectionVO();
				articleCollectionVO.setArticleCollectionNo(rs.getInt("article_collection_no"));
				articleCollectionVO.setArticleNo(rs.getInt("article_no"));
				articleCollectionVO.setMemberId(rs.getInt("member_id"));
				articleCollectionVO.setCreateTimestamp(rs.getTimestamp("created_timestamp"));
				list.add(articleCollectionVO); // Store the row in the list
			}

			// Handle any Util.DRIVER errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database Util.DRIVER. "
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

	public static void main(String[] args) {
		ArticleCollectionJDBCDAOIm dao = new ArticleCollectionJDBCDAOIm();

		// 新增
//		ArticleCollectionVO acV01 = new ArticleCollectionVO();
//		acV01.setArticleNo(1);
//		acV01.setMemberId(3);
//		
		// 修改
//		ArticleCollectionVO ac02 = new ArticleCollectionVO();
//		ac02.setArticleNo(1);
//		ac02.setMemberId(3);
//		ac02.setArticleCollectionNo(8);
//		dao.update(ac02);

		 //刪除
//		dao.delete(2);

//		 查詢
//		ArticleCollectionVO ac03 =  dao.findByPrimaryKey(4);
//		System.out.println(ac03);

		// 查詢

//		List<ArticleCollectionVO> list = dao.getAll();
//		for (ArticleCollectionVO aRcr : list) {
//			System.out.println(aRcr);
//		}
	}
}
