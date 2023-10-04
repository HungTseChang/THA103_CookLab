package com.cooklab.article_sub_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.Util;

public class ArticleSubReportJDBCDAO implements ArticleSubReportDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO article_sub_report (article_sub_no,reporter_id,reporting_reason,reporting_status) values(?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT article_sub_report_no,article_sub_no,reporter_id,reporting_reason,reporting_status,created_timestamp From article_sub_report order by article_sub_report_no";
	private static final String GET_ONE_STMT = "SELECT article_sub_report_no,article_sub_no,reporter_id,reporting_reason,reporting_status,created_timestamp From article_sub_report where article_sub_report_no=?";
	private static final String DELETE = "DELETE FROM article_sub_report where article_sub_report_no = ?";
	private static final String UPDATE = "UPDATE article_sub_report set article_sub_no=?, reporter_id=?, reporting_reason=?, reporting_status=? where article_sub_report_no=?";

	@Override
	public void insert(ArticleSubReportVO articleSubReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleSubReportVO.getArticleSubNo());
			pstmt.setInt(2, articleSubReportVO.getReporterId());
			pstmt.setString(3, articleSubReportVO.getReportingReason());
			pstmt.setInt(4, articleSubReportVO.getReportingStatus());

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

	@Override
	public void update(ArticleSubReportVO articleSubReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, articleSubReportVO.getArticleSubNo());
			pstmt.setInt(2, articleSubReportVO.getReporterId());
			pstmt.setString(3, articleSubReportVO.getReportingReason());
			pstmt.setInt(4, articleSubReportVO.getReportingStatus());

			pstmt.setInt(5, articleSubReportVO.getArticleSubReportNo());

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

	@Override
	public void delete(Integer articleSubReportNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleSubReportNo);

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

	@Override
	public ArticleSubReportVO findByPrimaryKey(Integer articleSubReportNo) {
		ArticleSubReportVO articleSubReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, articleSubReportNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleSubReportVO = new ArticleSubReportVO();
				articleSubReportVO.setArticleSubReportNo(rs.getInt("article_sub_report_no"));
				articleSubReportVO.setArticleSubNo(rs.getInt("article_sub_no"));
				articleSubReportVO.setReporterId(rs.getInt("reporter_id"));
				articleSubReportVO.setReportingReason(rs.getString("reporting_reason"));
				articleSubReportVO.setReportingStatus(rs.getByte("reporting_status"));
				articleSubReportVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

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
		return articleSubReportVO;
	}

	@Override
	public List<ArticleSubReportVO> getAll() {
		List<ArticleSubReportVO> list = new ArrayList<ArticleSubReportVO>();
		ArticleSubReportVO articleSubReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleSubReportVO = new ArticleSubReportVO();

				articleSubReportVO.setArticleSubReportNo(rs.getInt("article_sub_report_no"));
				articleSubReportVO.setArticleSubNo(rs.getInt("article_sub_no"));
				articleSubReportVO.setReporterId(rs.getInt("reporter_id"));
				articleSubReportVO.setReportingReason(rs.getString("reporting_reason"));
				articleSubReportVO.setReportingStatus(rs.getByte("reporting_status"));
				articleSubReportVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

				list.add(articleSubReportVO); // Store the row in the list
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

	public static void main(String[] args) {
		ArticleSubReportJDBCDAO dao = new ArticleSubReportJDBCDAO();

//		ArticleSubReportVO articleSubReportVO = new ArticleSubReportVO( 5 ,1 , "�ڴN�O�Q���|�A�̩�", 1); 
//		dao.insert(articleSubReportVO);

//		ArticleSubReportVO articleSubReportVO = new ArticleSubReportVO( 2 ,3 , "拉拉拉拉", 1); 
//		articleSubReportVO.setArticleSubReportNo(5);
//		dao.update(articleSubReportVO);

//		dao.delete(6);

		
//		ArticleSubReportVO articleSubReportVO = dao.findByPrimaryKey(5);
//		System.out.println(articleSubReportVO);

//		List<ArticleSubReportVO> list = dao.getAll();
//		for (ArticleSubReportVO aArticleSubReportVO : list) {
//			System.out.println(aArticleSubReportVO);
//			
//		}

	}
}