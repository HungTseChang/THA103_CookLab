package com.cooklab.support_form.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.util.*;
public class SupportFormJDBCDAOIm implements SupportFormDAO{
	
	private static final String INSERT_STMT = "INSERT INTO support_form (real_name,support_form_category_id,reply_email,form_context,form_title ) VALUES (?, ?, ?,?, ?)";
	private static final String GET_ALL_STMT = "SELECT form_no,real_name,support_form_category_id,reply_email,form_context,form_title,created_timestamp from support_form order by form_no";
	private static final String GET_ONE_STMT = "SELECT form_no,real_name,support_form_category_id,reply_email,form_context,form_title,created_timestamp from support_form where form_no = ?";
	private static final String DELETE = "DELETE FROM support_form where form_no = ?";
	private static final String UPDATE = "UPDATE support_form set real_name=?, support_form_category_id=?, reply_email=?, form_context=?, form_title=? where form_no = ?";

	
	public static void main(String[] args) {
		SupportFormJDBCDAOIm dao = new SupportFormJDBCDAOIm();

//		 
		SupportFormVO supportFormVO1 = new SupportFormVO();
		supportFormVO1.setRealName("ken");
		supportFormVO1.setSupportFormCategoryId(2);
		supportFormVO1.setReplyEmail("aaa@aaa.com");
		supportFormVO1.setFormContext("abcdefgs");
		supportFormVO1.setFormTitle("test");
		dao.insert(supportFormVO1);

//		
		SupportFormVO supportFormVO2 = new SupportFormVO();
		supportFormVO2.setRealName("amy");
		supportFormVO2.setSupportFormCategoryId(2);
		supportFormVO2.setReplyEmail("aaa@aaa.com");
		supportFormVO2.setFormContext("abcdefgs");
		supportFormVO2.setFormTitle("test");
		supportFormVO2.setFormNo(1);
		dao.update(supportFormVO2);

		
//		dao.delete(1);

//		 
		SupportFormVO supportFormVO3 = dao.findByPrimaryKey(1);
		System.out.print(supportFormVO3);
		System.out.println("---------------------");

		// 
		List<SupportFormVO> list = dao.getAll();
		for (SupportFormVO aSupportForm : list) {
			System.out.print(aSupportForm);
			System.out.println();
		}

	}


	@Override
	public void insert(SupportFormVO supportFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, supportFormVO.getRealName());
			pstmt.setInt(2, supportFormVO.getSupportFormCategoryId());
			pstmt.setString(3, supportFormVO.getReplyEmail());
			pstmt.setString(4, supportFormVO.getFormContext());
			pstmt.setString(5, supportFormVO.getFormTitle());

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
	public void update(SupportFormVO supportFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, supportFormVO.getRealName());
			pstmt.setInt(2, supportFormVO.getSupportFormCategoryId());
			pstmt.setString(3, supportFormVO.getReplyEmail());
			pstmt.setString(4, supportFormVO.getFormContext());
			pstmt.setString(5, supportFormVO.getFormTitle());
			pstmt.setInt(6, supportFormVO.getFormNo());

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
	public void delete(Integer formNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, formNo);

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
	public SupportFormVO findByPrimaryKey(Integer formNo) {
		SupportFormVO supportFormVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, formNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				supportFormVO = new SupportFormVO();
				supportFormVO.setRealName(rs.getString("real_name"));
				supportFormVO.setSupportFormCategoryId(rs.getInt("support_form_category_id"));
				supportFormVO.setReplyEmail(rs.getString("reply_email"));
				supportFormVO.setFormContext(rs.getString("form_context"));
				supportFormVO.setFormTitle(rs.getString("form_title"));
				supportFormVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				supportFormVO.setFormNo(rs.getInt("form_no"));
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
		return supportFormVO;
	}


	@Override
	public List<SupportFormVO> getAll() {
		List<SupportFormVO> list = new ArrayList<SupportFormVO>();
		SupportFormVO supportFormVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				supportFormVO = new SupportFormVO();
				supportFormVO.setRealName(rs.getString("real_name"));
				supportFormVO.setSupportFormCategoryId(rs.getInt("support_form_category_id"));
				supportFormVO.setReplyEmail(rs.getString("reply_email"));
				supportFormVO.setFormContext(rs.getString("form_context"));
				supportFormVO.setFormTitle(rs.getString("form_title"));
				supportFormVO.setCreatedTimestamp(rs.getTimestamp("created_timestamp"));
				supportFormVO.setFormNo(rs.getInt("form_no"));
				list.add(supportFormVO); // Store the row in the list
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
