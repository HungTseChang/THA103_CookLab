package com.cooklab.article_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;

public class ArticleReportJDBCDAOIm implements ArticleReportDAO {
	private static final String INSERT_STMT = "INSERT INTO article_report (article_no,reporter_id,reporting_reason,reporting_status,created_timestamp) VALUES (?, ?, ?, ?, now())";
	private static final String GET_ALL_STMT = "SELECT article_report_no,article_no,reporter_id,reporting_reason,reporting_status,created_timestamp FROM article_report order by article_report_no";
	private static final String GET_ONE_STMT = "SELECT article_report_no,article_no,reporter_id,reporting_reason,reporting_status,created_timestamp FROM article_report where article_report_no = ?";
	private static final String DELETE = "DELETE FROM article_report where article_report_no = ?";
	private static final String UPDATE = "UPDATE article_report set article_no=?, reporter_id=?, reporting_reason=?, reporting_status=?, created_timestamp=now() where article_report_no = ?";

	@Override
	public void insert(ArticleReportVO ArticleReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ArticleReportVO.getArticleNo());
			pstmt.setInt(2, ArticleReportVO.getReporterId());
			pstmt.setString(3, ArticleReportVO.getReportingReason());
			pstmt.setByte(4, ArticleReportVO.getReportingStatus());

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
	public void update(ArticleReportVO ArticleReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ArticleReportVO.getArticleNo());
			pstmt.setInt(2, ArticleReportVO.getReporterId());
			pstmt.setString(3, ArticleReportVO.getReportingReason());
			pstmt.setByte(4, ArticleReportVO.getReportingStatus());
			pstmt.setInt(5, ArticleReportVO.getArticleReportNo());

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
	public void delete(Integer getArticle_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, getArticle_no);

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
	public ArticleReportVO findByPrimaryKey(Integer articleReportNo) {

		ArticleReportVO ArticleReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleReportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empArticleReportVO 也稱為 Domain objects
				ArticleReportVO = new ArticleReportVO();
				ArticleReportVO.setArticleReportNo(rs.getInt("article_report_no"));
				ArticleReportVO.setArticleNo(rs.getInt("article_no"));
				ArticleReportVO.setReporterId(rs.getInt("reporter_id"));
				ArticleReportVO.setReportingReason(rs.getString("reporting_reason"));
				ArticleReportVO.setReportingStatus(rs.getByte("reporting_status"));
				ArticleReportVO.setCreatedTimestamp(rs.getDate("created_timestamp"));

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
		return ArticleReportVO;
	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<ArticleReportVO> getAll() {
		List<ArticleReportVO> list = new ArrayList<ArticleReportVO>();
		ArticleReportVO ArticleReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empArticleReportVO 也稱為 Domain objects
				ArticleReportVO = new ArticleReportVO();
				ArticleReportVO.setArticleReportNo(rs.getInt("article_report_no"));
				ArticleReportVO.setArticleNo(rs.getInt("article_no"));
				ArticleReportVO.setReporterId(rs.getInt("reporter_id"));
				ArticleReportVO.setReportingReason(rs.getString("reporting_reason"));
				ArticleReportVO.setReportingStatus(rs.getByte("reporting_status"));
				ArticleReportVO.setCreatedTimestamp(rs.getDate("created_timestamp"));

				list.add(ArticleReportVO); // Store the row in the list
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

		ArticleReportJDBCDAOIm dao = new ArticleReportJDBCDAOIm();

		// 新增
//			ArticleReportVO ArticleReportVO1 = new ArticleReportVO();
//			ArticleReportVO1.setArticleNo(Integer.valueOf(1));
//			ArticleReportVO1.setReporterId(Integer.valueOf(1));
//			ArticleReportVO1.setReportingReason("排版好累");
//			ArticleReportVO1.setReportingStatus(Byte.valueOf((byte) 1));
//			dao.insert(ArticleReportVO1);

//			// 修改
		ArticleReportVO ArticleReportVO2 = new ArticleReportVO();
		ArticleReportVO2.setArticleReportNo(Integer.valueOf(1));
		ArticleReportVO2.setArticleNo(Integer.valueOf(1));
		ArticleReportVO2.setReporterId(Integer.valueOf(1));
		ArticleReportVO2.setReportingReason("排版真的好累ZZ");
		ArticleReportVO2.setReportingStatus(Byte.valueOf((byte) 0));
		dao.update(ArticleReportVO2);

////			// 刪除
//			dao.delete(2);
////
////			// 查詢
//			ArticleReportVO ArticleReportVO3 = dao.findByPrimaryKey(1);
//			System.out.print(ArticleReportVO3.getArticleReportNo() + ",");
//			System.out.print(ArticleReportVO3.getArticleNo() + ",");
//			System.out.print(ArticleReportVO3.getReporterId() + ",");
//			System.out.print(ArticleReportVO3.getReportingReason() + ",");
//			System.out.print(ArticleReportVO3.getReportingStatus() + ",");
//			System.out.println(ArticleReportVO3.getCreatedTimestamp() + ",");
//			System.out.println("---------------------");
//
////			// 查詢
		List<ArticleReportVO> list = dao.getAll();
		for (ArticleReportVO aArticleReportVO : list) {
			System.out.print(aArticleReportVO.getArticleReportNo() + ",");
			System.out.print(aArticleReportVO.getArticleNo() + ",");
			System.out.print(aArticleReportVO.getReporterId() + ",");
			System.out.print(aArticleReportVO.getReportingReason() + ",");
			System.out.print(aArticleReportVO.getReportingStatus() + ",");
			System.out.println(aArticleReportVO.getCreatedTimestamp() + ",");
			System.out.println("---------------------");
		}
	}
}
