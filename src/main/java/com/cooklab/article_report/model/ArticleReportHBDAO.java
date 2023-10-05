package com.cooklab.article_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;
import com.cooklab.util.Util;

public class ArticleReportHBDAO {
	
	public void insert(ArticleReportVO ArticleReportVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

           	session.save(ArticleReportVO);
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	

	}

//============================insert完結=======================================
	public void update(ArticleReportVO ArticleReportVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

       	ArticleReportVO ArticleReportVO1  = session.get(ArticleReportVO.class,ArticleReportVO.getArticleReportNo());
       	if(ArticleReportVO1 != null) {
       		ArticleReportVO1.getArticleNo()
       		ArticleReportVO1.getReporterId()
       		ArticleReportVO1.getReportingReason()
       		ArticleReportVO1
       		ArticleReportVO1.setReportingReason("");
       		
       	}
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
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
				ArticleReportVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

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
				ArticleReportVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));

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
}
